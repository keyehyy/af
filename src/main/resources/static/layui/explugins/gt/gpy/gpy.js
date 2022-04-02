
/**
 @ Name：高拍仪接口，可以调用插件直接使用高拍仪
 @ Author：SuperYoung
 @ License：MIT
 */

layui.define(['form','layer'], function(exports){
    var $ = layui.$
        ,form = layui.form
        ,layer = layui.layer
        
        //字符常量
        ,MOD_NAME = 'gpy', ELEM = '.layui-gpy'
        
        //外部接口
        ,gpy = {
            index: layui.gpy ? (layui.gpy.index + 10000) : 0
            
            //设置全局项
            ,set: function(options){
                var that = this;
                that.config = $.extend({}, that.config, options);
                return that;
            }
            
            //事件监听
            ,on: function(events, callback){
                return layui.onevent.call(this, MOD_NAME, events, callback);
            }
        }
        
        //操作当前实例
        ,thisIns = function(){
            var that = this
                ,options = that.config
                ,id = options.id || options.index;
            
            return {
                reload: function(options){
                    that.reload.call(that, options);
                }
                ,config: options
            }
        }
        
        //构造器
        ,Class = function(options){
            var that = this;
            that.index = ++gpy.index;
            that.config = $.extend({}, that.config, gpy.config, options);
            that.render();
        };
    
    //默认配置
    Class.prototype.config = {
        code:""
        ,once:false
        //其他参数
        //……
    };
    
    //渲染视图
    Class.prototype.render = function(){
        var that = this
            ,options = that.config;
        
        options.elem = $(options.elem);
        
        options.elem.click(()=>{
            let gpyOpenIndex = layer.open({
                type: 1
                ,title:"高拍仪采集"
                ,area:['600px','530px']
                ,resize:false
                // ,btn:['采集','取消']
                // ,yes:function (index, layero) {
                //     options.success("测试")
                //     // let body = layer.getChildFrame('body');
                //     // let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //     // body.find('#addWpbg').click()
                //     // iframeWin.submit();
                // }
                // ,btn2:function () {
                //
                // }
                ,success:function () {
                    loadStart()
                    let timeOut = setTimeout(function () {
                        loadEnd();
                        layer.msg("链接高拍仪超时");
                    },10000)
                    window.useCameraWs = new WebSocket(wsIpGpy);
                    window.onbeforeunload = function () {
                        useCameraWs.send("bStopPlay()");
                    };
                    useCameraWs.onerror = function (event) {
                    
                    };
                    useCameraWs.onopen = function (event) {
                        useCameraWs.send('bStartPlay');
                        loadEnd()
                        clearTimeout(timeOut)
                    };
                    useCameraWs.onclose = function (event) {
                        useCameraWs.send("bStopPlay()");
                    };
                    useCameraWs.onmessage = function (event) {
                        onMessage(event);
                    };
                    let begin_data = "data:image/jpeg;base64,";
                    function onMessage(event) {
                        if (event.data.indexOf('BarCodeTransferBegin') >= 0) {
                            alert(event.data.replace('BarCodeTransferBegin', '').replace('BarCodeTransferEnd', ''));
                        } else if (event.data.indexOf('BeginbSaveJPGtrueEndbSaveJPG') >= 0) {
                            console.log('存储成功');
                        } else if (event.data.indexOf('BeginbDirIsExistFALSEEndbDirIsExist') >= 0) {
                            console.log('路径不存在，开始创建路径C:\\GTPhoto');
                            useCameraWs.send("bCreateDir(C:\\GTPhotos)");
                        } else if (event.data.indexOf('BeginBase64') >= 0) {
                            $.ajax({
                                url: newip + 'wpbg/uploadBase64Zp',
                                type: 'post',
                                async: true,
                                data: {
                                    photo: event.data.replace('BeginBase64', '').replace('EndBase64', ''),
                                    code: options.code
                                },
                                beforeSend: function () {
                                    loadStart();
                                },
                                complete: function () {
                                    loadEnd();
                                },
                                success: function (res) {
                                    if (res.code === 0) {
                                        options.success(res)
                                        if (options.once){
                                            useCameraWs.send("bStopPlay()");
                                            layer.close(gpyOpenIndex)
                                        }
                                    } else {
                                        layer.msg(res.msg);
                                    }
                                },
                                error: function (err) {
                                    layer.msg('网络连接异常');
                                }
                            });
                        } else {
                            document.getElementById('gpyImgDiv').src = begin_data + event.data;
                        }
                    }
                    function gpyGetImg() {
                    
                    }
                    $("#gpyGetImg").click(()=>{
                        useCameraWs.send("sGetBase64");
                    })
                    $("#gpyChange1").click(()=>{
                        useCameraWs.send("bStartPlay");
                    })
                    $("#gpyChange2").click(()=>{
                        useCameraWs.send("bStartPlay2");
                    })
                    
                }
                ,end:function () {
                
                }
                ,content:`
                    <div class="gt-gpy-main">
                        <div>
                            <img id="gpyImgDiv" src="" width='560px' height='420px'>
                        </div>
                        <div style="float: right;margin-top: 5px">
                            <div class="layui-btn-group">
                                <button id="gpyChange1" type="button" class="layui-btn ">切换主镜头</button>
                                <button id="gpyChange2" type="button" class="layui-btn ">切换副镜头</button>
                            </div>
                            <div class="layui-btn-group">
                                <button id="gpyGetImg" type="button" class="layui-btn layui-btn-normal">采集</button>
                            </div>
                        </div>
                    </div>
                `
            });
        })
        
    }
    
    //核心入口
    gpy.render = function(options){
        var ins = new Class(options);
        return thisIns.call(ins);
    };
    
    //加载组件所需样式
    layui.link(layui.cache.base + 'gt/gpy/gpy.css?v=1', function(){
        //样式加载完毕的回调
        
    }, 'gpy'); //此处的“gpy”要对应 gpy.css 中的样式： html #layuicss-gpy{}
    
    exports('gpy', gpy);
});
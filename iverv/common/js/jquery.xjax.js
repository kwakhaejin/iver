; (function($) {
    $.xjax = {}
    $.xjax.status403 = false;
})(jQuery);

(function ($) { 
    var originalVal = $.fn.val; 
    $.fn.val = function(value) { 
        if (arguments.length == 0) {
            return originalVal.call(this); 
        }

        if (typeof value != 'undefined' && typeof value == 'string') {
            // setter invoked, do processing 
                value = value.replace(/&lt;/g, '<').replace(/&gt;/g, '>'); 
        } 
        return originalVal.call(this, value); 
    };
})(jQuery);

// _options = {
//    data : object
//    datatype : default 'json'
//    success function (data, status, xhr) {}
//    error : function (xhr, status, error) {}
// }

(function(x) {
    x.getAjaxOptions = function (_options) {
        _options.url = encodeURI(_options.url);
        var ajaxOpt = {
            type: _options.type,
            url: _options.url,
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            success: function (data, status, xhr) {
                try {

                    // console.log({data:data,status:status,statusText:xhr.statusText});
                    if (data.error && _options.failonerror != false) {
                        alert(data.error);
                    }
        
                    if (_options.success != undefined) {
                        _options.success(data, status, xhr);
                    }

                } catch (e) {

                    console.error(e);
                    
                }
            },
            error: function (xhr, status, error) {
                try {

                    if (xhr.status == 403) {
                        $.xjax.status403 = true;
                        getUserDetails();
                    }
                    
                    console.log({error:error.name,status:status,statusText:xhr.statusText});
                    if (_options.error != undefined) {
                        _options.error(xhr, xhr.status, error);
                    }

                } catch (e) {
                    console.error(e);
                }
            },
            beforeSend : function(){
                if (_options.beforeSend != undefined) {
                    _options.beforeSend();
                }
            },
            complete : function(){
                if (_options.complete != undefined) {
                    _options.complete();
                }
            }
        };
    
        if (_options.type == 'GET') {
        } else {
            ajaxOpt.contentType = "application/json; charset=UTF-8";
        }
    
        $.each(_options, function(e, v) {
            //if (_options.type != 'GET' && e == 'data' && typeof v == 'object' && v.constructor.name != 'FormData') {
            if (_options.type != 'GET' && e == 'data' && typeof v == 'object' && v.toString() != '[object FormData]') {
                ajaxOpt.data = JSON.stringify(v);
            } else if (e == 'success' || e == 'error') {
            } else {
                ajaxOpt[e] = v;
            }
        });
    
        return ajaxOpt;
    }
})($.xjax);

(function(x) {
    x.replaceXSSFilters = function (_options) {
        if (_options.xss == false)
            return ;
    
        x.reteriveXSSFilter(_options.data); 
        // console.log("#### XSS Filter ## ", _options.data);
    }
})($.xjax);

(function(x) {
    x.reteriveXSSFilter = function (data) {
        if (typeof(data) == 'object') {
            $.each(data, function(i, e) { 
                if (typeof(e) == 'string') { 
                    data[i] = e.replace(/</g, '&lt;').replace(/>/g, '&gt;'); 
                } else if (typeof(e) == 'object') {
                    x.reteriveXSSFilter(e);
                }
            });
        } else if (typeof(data) == 'string') {
            data = data.replace(/</g, '&lt;').replace(/>/g, '&gt;'); 
        }
    }
})($.xjax);

(function($) {
    var m = 'GET';
    $.xjax.get = function(_url, _options, bypass) {
        //console.log("[" + m + "] " + _url);

        if (_options == undefined) {
            _options = {};
        }

        _options.type = m;
        _options.url = _url;

        if ($.xjax.status403 == true) {
            window.location.href = defaultHomePath;
            return ;
        }
        
        if (bypass)
            $.ajax(_options);
        else
            $.ajax($.xjax.getAjaxOptions(_options));
    }
})(jQuery);

(function($) {
    var m = 'POST';
    $.xjax.post = function(_url, _options) {
        // console.log("[" + m + "] " + _url);

        if (_options == undefined) {
            _options = {};
        }

        $.xjax.replaceXSSFilters(_options);

        _options.type = m;
        _options.url = _url;

        if ($.xjax.status403 == true) {
            window.location.href = defaultHomePath;
            return ;
        }
        $.ajax($.xjax.getAjaxOptions(_options));
    }
})(jQuery);

(function($) {
    var m = 'PUT';
    $.xjax.put = function(_url, _options) {
        // console.log("[" + m + "] " + _url);

        if (_options == undefined) {
            _options = {};
        }

        $.xjax.replaceXSSFilters(_options);

        _options.type = m;
        _options.url = _url;

        if ($.xjax.status403 == true) {
            window.location.href = defaultHomePath;
            return ;
        }
        $.ajax($.xjax.getAjaxOptions(_options));
    }
})(jQuery);

(function($) {
    var m = 'DELETE';
    $.xjax.delete = function (_url, _options) {
        // console.log("[" + m + "] " + _url);

        if (_options == undefined) {
            _options = {};
        }

        _options.type = m;
        _options.url = _url;

        if ($.xjax.status403 == true) {
            window.location.href = defaultHomePath;
            return ;
        }
        $.ajax($.xjax.getAjaxOptions(_options));
    }
})(jQuery);

(function($) {
    var m = 'POST';
    $.xjax.multipart = function(_url, _options) {
        // console.log("[multipart:" + m + "] " + _url);

        if (_options == undefined) {
            _options = {};
        }

        if (!_options.type)
            _options.type = m;

        _options.url = _url;
        _options.xhrFields = {withCredentials: true};
        _options.crossDomain = true;

        if ($.xjax.status403 == true) {
            window.location.href = defaultHomePath;
            return ;
        }
        $.ajax(_options);
    }
})(jQuery);

(function($) {
    if ($.xjax.status403 == true) {
        window.location.href = defaultHomePath;
        return ;
    }
    $.xjax.loadScript = function (url, callback) {
        // console.log("load script : " + url);
        $.ajax({
            url: url,
            dataType: 'script',
            // async: false,
            success: function (data, status, xhr) {
                if (callback != undefined) {
                    callback(data, status, xhr);
                }
            },
            error: callback
        });
    }
})(jQuery);

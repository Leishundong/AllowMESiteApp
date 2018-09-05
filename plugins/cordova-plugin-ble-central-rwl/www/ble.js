"use strict";

module.exports = {
    print: function (message, success, failure) {
        var alreadycallfailure = false;
        setTimeout(
            function () {
                if (!alreadycallfailure) {
                    failure.call(null, "30秒时间到超时了，请把手机蓝牙开关重启下，再重启打印机，然后稍等10秒钟再打印！");
                }
            },
            30000);
        cordova.exec(success, function (msg) {
            alreadycallfailure = true;
            failure.call(null, msg);
        }, 'BLE', 'print', [message]);
    }
};


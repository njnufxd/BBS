
    /**
     * 判空
     *
     * @param obj
     * @returns {boolean}
     */
    function isNull(obj) {
        if (obj == null || obj == undefined || obj.trim() == "") {
            return true;
        }
        return false;
    }

/**
 * 参数长度验证
 *
 * @param obj
 * @param length
 * @returns {boolean}
 */
function validLength(obj, length) {
    if (obj.trim().length < length) {
        return true;
    }
    return false;
}

/**
 * url验证
 *
 * @param str
 * @returns {boolean}
 */
function isURL(str_url) {
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}"
        + "|"
        + "([0-9a-zA-Z_!~*'()-]+\.)*"
        + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\."
        + "[a-zA-Z]{2,6})"
        + "(:[0-9]{1,4})?"
        + "((/?)|"
        + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var re = new RegExp(strRegex);
    if (re.test(str_url)) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 用户名称验证 4到16位（字母，数字，下划线，减号）
 *
 * @param userName
 * @returns {boolean}
 */
function validUserName(userName) {
    var pattern = /^[\u4E00-\u9FA5\uF900-\uFA2D|\w]{2,8}$/;
    if (pattern.test(userName.trim())) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 邮箱号正则验证
 * @returns {boolean}
 */
function validEmail(email) {
    var strRegex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (strRegex.test(email)) {
        return true;
    }
    return false;
}

/**
 * 手机号正则验证
 * @returns {boolean}
 */
function validPhoneNumber(phone) {
    if ((/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
        return true;
    }
    return false;
}

/**
 * 正则匹配2-18位的中英文字符串
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_18(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,18}$/;
    if (pattern.test(str.trim())) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 正则匹配2-100位的中英文字符串
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_100(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,100}$/;
    if (pattern.test(str.trim())) {
        return (true);
    } else {
        return (false);
    }
}

/**
 * 用户密码验证 最少6位，最多20位字母或数字的组合
 *
 * @param password
 * @returns {boolean}
 */
function validPassword(password) {
    var pattern = /^[a-zA-Z0-9]{6,20}$/;
    if (pattern.test(password.trim())) {
        return (true);
    } else {
        return (false);
    }
}
layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;

    // 监听导航栏点击事件
    element.on('nav(test)', function (elem) {
        var contentId = elem.parent().attr('data-content');
        // 切换内容区域
        document.querySelectorAll('.content').forEach(function (content) {
            content.classList.remove('active');
        });
        document.getElementById(contentId).classList.add('active');

        // 切换选中状态
        document.querySelectorAll('.layui-nav-item').forEach(function (navItem) {
            navItem.classList.remove('layui-this');
        });
        elem.parent().addClass('layui-this');
    });

    // 搜索图标点击事件
    document.getElementById('search-icon').addEventListener('click', function () {
        var searchInput = document.getElementById('search-input');
        if (searchInput.classList.contains('active')) {
            searchInput.classList.remove('active');
        } else {
            searchInput.classList.add('active');
            searchInput.focus();
        }
        console.log("search");
    });

    // 通知图标点击事件
    document.getElementById('notification-icon').addEventListener('click', function () {
        layer.open({
            type: 1,
            title: '消息列表',
            shadeClose: true,
            area: '300px',
            content: '<div style="padding: 20px;">' +
                '<p>消息1</p>' +
                '<p>消息2</p>' +
                '<p>消息3</p>' +
                '</div>'
        });
    });
});
document.getElementById("search-input").onkeydown = function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        handleSubmit();
    }
    console.log(event.keyCode);
}
function handleSubmit() {
    var value = document.getElementById("search-input").value;
    if (value == "") {
        alert("请输入内容");
    } else {
        window.location.href = "/index?keyword=" + value;
    }
}
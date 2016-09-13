window.onload = drag;

    function drag() {
        var oTitle = getByClass('login_logo_web', 'loginPanel')[0];
        // 拖曳
        oTitle.onmousedown = fnDown;
    }

    function fnDown(event) {
        event = event || window.event;
        var oDrag = document.getElementById('loginPanel'),
            // 光标按下时光标和面板之间的距离
            disX = event.clientX - oDrag.offsetLeft,
            disY = event.clientY - oDrag.offsetTop;
        // 移动
        document.onmousemove = function(event) {
                event = event || window.event;
                fnMove(event, disX, disY);
            }
            // 释放鼠标
        document.onmouseup = function() {
            document.onmousemove = null;
            document.onmouseup = null;
        }
    }

    function fnMove(e, posX, posY) {
        var oDrag = document.getElementById('loginPanel'),
            l = e.clientX - posX,
            t = e.clientY - posY,
            winW = document.documentElement.clientWidth || document.body.clientWidth,
            winH = document.documentElement.clientHeight || document.body.clientHeight,
            maxW = winW - oDrag.offsetWidth,
            maxH = winH - oDrag.offsetHeight;
        if (l < 0) {
            l = 0;
        } else if (l > maxW) {
            l = maxW;
        }
        if (t < 0) {
            t = 0;
        } else if (t > maxH) {
            t = maxH;
        }
        oDrag.style.left = l + 'px';
        oDrag.style.top = t + 'px';
    }

    function getByClass(clsName, parent) {
        var oParent = parent ? document.getElementById(parent) : document,
            eles = [],
            elements = oParent.getElementsByTagName('*');

        for (var i = 0, l = elements.length; i < l; i++) {
            if (elements[i].className == clsName) {
                eles.push(elements[i]);
            }
        }
        return eles;
    }
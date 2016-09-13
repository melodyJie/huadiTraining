window.addEventListener('load', function() {
    var L4 = document.getElementById("li-4");
    var begin = null;
    //球容器的移动
    L4.style.animation = "L-move ease-out 2s";

    //球的旋转
    var L4_L = document.getElementById("L-4");
    L4_L.style.visibility = "visible";

    L4_L.style.transition = "transform ease-out 2s";
    L4_L.style.transform = "rotate(720deg)";

    L4.addEventListener('animationstart', L4start, false);
    L4.addEventListener('animationend', function() {
        clearInterval(begin);
    }, false);

    function L4start() {
        begin = setInterval(function() {
            var matrix = window.getComputedStyle(L4).transform.toString();
            //对矩阵进行处理
            matrix = matrix.substring(7, matrix.length - 1);
            var matArr = matrix.split(",");
            var translatex = -matArr[4];
            //console.log(matArr[4]);
            if (translatex < 200) {
                document.querySelector("#li-3").style.display = "block";
            }
            if (translatex < 400) {
                document.querySelector("#li-2").style.display = "block";
            }
            if (translatex < 600) {
                document.querySelector("#li-1").style.display = "block";
            }
            if (translatex < 800) {
                document.querySelector("#li-0").style.display = "block";
            }

        }, 50);
    }

    /*触碰提示音*/
    $("#list-product li").on('mouseover', function(e) {
        if (checkHover(e, this)) {
            if (ballmusic.paused) {
                ballmusic.play();
            } else {
                ballmusic.currentTime = 0;
            }
        }
    })
    /** 
     * 下面是一些基础函数，解决mouseover与mouserout事件不停切换的问题（问题不是由冒泡产生的） 
     */
    function checkHover(e, target) {
        if (getEvent(e).type == "mouseover") {
            return !contains(target, getEvent(e).relatedTarget || getEvent(e).fromElement) && !((getEvent(e).relatedTarget || getEvent(e).fromElement) === target);
        } else {
            return !contains(target, getEvent(e).relatedTarget || getEvent(e).toElement) && !((getEvent(e).relatedTarget || getEvent(e).toElement) === target);
        }
    }

    function contains(parentNode, childNode) {
        if (parentNode.contains) {
            return parentNode != childNode && parentNode.contains(childNode);
        } else {
            return !!(parentNode.compareDocumentPosition(childNode) & 16);
        }
    }
    //取得当前window对象的事件  
    function getEvent(e) {
        return e || window.event;
    }

}, false);

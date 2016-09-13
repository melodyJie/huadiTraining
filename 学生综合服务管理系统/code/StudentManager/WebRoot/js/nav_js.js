$(function(){
	//document 事件
	$(document).on("click",function(e){
        var e=e||event;
        // class为hide_ul的是菜单, id为icon_btn的是打开菜单的按钮
        if($(e.target).closest(".hide_ul").length == 0&&$(e.target).closest("#icon_btn").length == 0){
        	//点击class为hide_ul之外且id不是icon_btn的元素，则触发
        	$(".hide_ul").hide(100);
        }
	})
	//功能导航栏事件
	$(".unfold_ul").on("click",function(){
		$(this).siblings(".nav_hide_ul").toggle(200);
	});
	//nav 显示隐藏功能事件
	$("#icon_btn").on("click",function(){
		if($(".hide_ul").is(":hidden")){
			$(".hide_ul").show(100);
		}
	});

})
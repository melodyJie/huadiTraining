    $().ready(function() {
        // 提交时验证表单
        var validator = $("#loginForm").validate({
            errorPlacement: function(error, element) {
                error.appendTo(element.parent());
            },
            errorElement: "span",
            messages: {
                user: {
                    required: " (必需字段)",
                    minlength: " (不能少于 3 个字母)"
                },
                password: {
                    required: " (必需字段)",
                    minlength: " (字母不能少于 5 个且不能大于 12 个)",
                    maxlength: " (字母不能少于 5 个且不能大于 12 个)"
                }
            }
        });
    });
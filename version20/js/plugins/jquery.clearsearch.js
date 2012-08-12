// Author: Stephen Korecky
// Website: http://stephenkorecky.com
// Plugin Website: http://github.com/skorecky/Add-Clear
(function ($) {
    $.fn.extend({
		addBackgroundClass: function(elem, backgroundClass) {
			return elem.each(function() {
				if (typeof backgroundClass == "string"
					&& backgroundClass != null
					&& !$(this).hasClass(backgroundClass)
				)
					$(this).addClass(backgroundClass);
			});
		},
		removeBackgroundClass: function(elem, backgroundClass) {
			return elem.each(function() {
				if (typeof backgroundClass == "string"
					&& backgroundClass != null
					&& $(this).hasClass(backgroundClass)
				)
					$(this).removeClass(backgroundClass);
			});
		},
        clearSearch: function (options) {
            var options = $.extend({
                closeImage: "img/clear.png",
                top: 0,
                right: 4,
                returnFocus: true,
                showOnLoad: false,
				backgroundClass: null
            }, options);

            $(this).wrap("<span style='position:relative;' class='add-clear-span'>");
            $(this).parent(".add-clear-span").append("<a href='#clear'>clear</a>");
            $(this).siblings("a[href='#clear']").css({
                'background': 'transparent url(' + options.closeImage + ') 0 0 no-repeat',
                'position': 'absolute',
                'top': options.top + 'px',
                'right': options.right + 'px',
                'width': '16px',
                'height': '16px',
                'overflow': 'hidden',
                'text-indent': '-9999px',
                'display': 'none'
            }, this);

            if ($(this).val().length >= 1 && options.showOnLoad === true) {
				this.addBackgroundClass($(this), options.backgroundClass);
                $(this).siblings("a[href='#clear']").show();
            }

			var thisFn = this;
            $(this).keyup(function () {
                if ($(this).val().length >= 1) {
					thisFn.removeBackgroundClass($(this), options.backgroundClass);
                    $(this).siblings("a[href='#clear']").show();
				} else { 
                    $(this).siblings("a[href='#clear']").hide();
					thisFn.addBackgroundClass($(this), options.backgroundClass);
				}
            });

            $("a[href='#clear']").click(function () {
                $(this).siblings("input").val("");
                $(this).hide();
				thisFn.addBackgroundClass($(this).siblings("input"), options.backgroundClass);
                if (options.returnFocus === true) {
                    $(this).siblings("input").focus();
                }
                return false;
            });
            return this;
        }
    });
})(jQuery);
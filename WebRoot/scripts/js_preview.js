/**
 * 图片预览js
 */

$(function () {
		/**
		 * 这个是头像修改预览用到的调用
		 */
		$("#file0").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
		
		/**
		 * 下面的是资源上传页面上传的四张图片的预览
		 */
	});

	jQuery.fn.extend({
		uploadPreview: function (opts) {
			var _self = this,
			_this = $(this);
			opts = jQuery.extend({
			Img: "ImgPr",
			Width: 100,
			Height: 100,
			ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
			Callback: function () {}
		}, opts || {});

		_self.getObjectURL = function (file) {
			var url = null;
			if (window.createObjectURL != undefined) {
				url = window.createObjectURL(file)
			} else if (window.URL != undefined) {
				url = window.URL.createObjectURL(file)
			} else if (window.webkitURL != undefined) {
				url = window.webkitURL.createObjectURL(file)
			}
			return url
		};
		_this.change(function () {
			if (this.value) {
				if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
					//alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
					var info=$("<span class='a2 error' id='theError'>只能是jpg,png,bmp,jpeg,gif</span>");
					$("#theError").remove();
					$("#theimg").before(info);
					this.value = "";
					return false
				}
				$("#theError").remove();
				if ($.browser.msie) {
					try {
						$("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
					} catch (e) {
						var src = "";
						var obj = $("#" + opts.Img);
						var div = obj.parent("div")[0];
						_self.select();
						if (top != self) {
							window.parent.document.body.focus()
					} else {
					_self.blur()
				}
			src = document.selection.createRange().text;
			document.selection.empty();
			obj.hide();
			obj.parent("div").css({
				'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
				'width': opts.Width + 'px',
				'height': opts.Height + 'px'
			});
			div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
		}
	} else {
		$("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
	}
		opts.Callback()
	}
	})
	}
});
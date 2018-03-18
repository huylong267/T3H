$(document).ready(function () {
    function readUrl(input) {
        if(input.files && input.files[0]){
            var reader = new  FileReader()
            reader.onload = function (e) {
                $("#preview-product-img").attr("src",e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#input-select-img-product").change(function () {
        readUrl(this);
        var formData = new FormData();
        NProgress.start();
        formData.append("file",$("#input-select-img-product")[0].files[0]);
        axios.post("/api/upload/upload-image",formData).then(function (res) {
            NProgress.done();
            if(res.data.success){
                $("#preview-product-img").attr("src",res.data.link);
                dataProduct={
                    image:res.data.link
                };
            }
        })
    }),function (err) {
        NProgress.done();
    }
     $('#datepicker-created-date-product').datetimepicker();

    $(".btn-save-product").on("click", function () {
        // if($("#input-product-name").val() === "" || $("#input-product-desc").val() === "" || dataProduct.image === undefined) {
        //     swal(
        //         'Error',
        //         'You need to fill all values',
        //         'error'
        //     );
        //     return;
        // }
        dataProduct.name = $('#input-product-name').val();
        dataProduct.shortDesc = $('#input-product-desc').val();
        dataProduct.createdDate = $("#datepicker-created-date-product").data("DateTimePicker").date().format("YYYY-MM-DD HH:mm:ss");
        NProgress.start();

        axios.post("/api/product/create-product", dataProduct).then(function(res){
            NProgress.done();
            console.log(res)
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving product',
                'error'
            );
        })
    });
});
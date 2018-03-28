$(function () {
    //solving the active menu problem
    //bag it kind of works even if you delete it. why? default does not work
    switch (menu) {
        case 'About Us':
            $('#about').addClass('active');
            break;
        case 'Contact':
            $('#contact').addClass('active');
            break;
        case 'ListProduct':
            $('#listProduct').addClass('active');
            break;
        case 'Manage Products':
            $('#manageProducts').addClass('active');
            break;
        case 'User Cart':
            $('#userCart').addClass('active');
            break;
        default:
            break;
    }

    // Protection from CSRF ATTACK (without it manage product slider ability does not work)
    var token = $('meta[name="_csrf"]').attr('content');
    var header = $('meta[name="_csrf_header"]').attr('content');
    if (token.length > 0 && header.length > 0) {
        // set the token header for the ajax request
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);

        });
    }

    //code for jquery datatable
    var $table = $('#productListTable');
    //execute the below code only when we have this table
    if ($table.length) {
        // console.log('Inside the table!');

        var jsonUrl = '';
        if (window.categoryId == '') {
            jsonUrl = window.contextRoot + '/json/data/all/products';
        }
        else {
            console.log(window.categoryId);
            jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
        }

        $table.DataTable({
            lengthMenu: [[3, 5, 10, -1], ['3 Records', '5 Records', '10 Records', 'ALL']],
            pageLength: 5,
            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },

            columns: [
                {
                    data: 'imageURL',
                    mRender: function (data, type, row) {
                        return '<img src = "' + window.contextRoot + '/resources/images/' + data + '" style="height: 100px" "/>';
                    }
                },
                {
                    data: 'name'
                },
                {
                    data: 'description',
                    bSortable: false
                },
                {
                    data: 'price',
                    mRender: function (data, type, row) {
                        return data;
                    }
                },
                {
                    data: 'availability',
                    mRender: function (data, type, row) {
                        if (data == true) {
                            return 'in stock'
                        }
                        return '<span style="color: red">out of stock</span>';
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';

                        str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></a> &#160;';
                        if (row.availability == false) {
                            if (userRole == 'ADMIN') {
                                str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

                            } else
                                str += '<a href="#" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                        } else {

                            if (userRole == 'ADMIN') {
                                str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
                            } else {
                                str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                            }
                        }
                        return str;
                    }
                }
            ]

        });
    }

    $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-dismissible").alert('close');
    });

    // --------------------------------------------

    $('.switch input[type="checkbox"]').on('change', function () {
        var checkbox = $(this);
        var checked = checkbox.prop('checked');
        var dMsg = (checked) ? 'You want to activate the product?' :
            'You want to deactivate the product?';
        var value = checkbox.prop('value');
        bootbox.confirm({
            size: 'medium',
            title: 'Product Activation & Deactivation',
            message: dMsg,
            callback: function (confirmed) {
                if (confirmed) {
                    console.log(value);
                    bootbox.alert({
                        size: 'medium',
                        title: 'Information',
                        message: 'You are going to perform operation on product' + value
                    });
                }
                else {
                    checkbox.prop('checked', !checked);
                }
            }
        });
    });


// -------------------------
//code for jquery ADMIN datatable
// -------------------------

    var $table2 = $('#adminProductsTable');
    //execute the below code only when we have this table
    if ($table2.length) {
        // console.log('Inside the table!');

        var jsonUrl2 = window.contextRoot + '/json/data/admin/all/products';


        $table2.DataTable({
            lengthMenu: [[10, 30, 50, -1], ['10 Records', '30 Records', '50 Records', 'ALL']],
            pageLength: 5,
            ajax: {
                url: jsonUrl2,
                dataSrc: ''
            },

            columns: [
                {
                    data: 'id'
                },
                {
                    data: 'imageURL',
                    mRender: function (data, type, row) {
                        return '<img src = "' + window.contextRoot + '/resources/images/' + data + '" style="height: 100px" "/>';
                    }
                },
                {
                    data: 'name'
                },
                {
                    data: 'description',
                    bSortable: false
                },
                {
                    data: 'price',
                    mRender: function (data, type, row) {
                        return data;
                    }
                },
                {
                    data: 'amountAvailable',
                    mRender: function (data, type, row) {
                        return data;
                    }
                },
                {
                    data: 'availability',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += ' <label class="switch">';
                        if (data) {
                            str += '  <input type="checkbox" checked="checked" value="' + row.id + '"/>';
                        } else {
                            str += '  <input type="checkbox" value="' + row.id + '"/>';
                        }
                        str += ' <div class="slider"></div></label>';
                        return str;
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning">';
                        str += '<span class="glyphicon glyphicon-pencil"></span></a>';
                        return str;
                    }
                }
            ],
            initComplete: function () {
                var api = this.api();
                api.$('.switch input[type="checkbox"]').on('change', function () {
                    var checkbox = $(this);
                    var checked = checkbox.prop('checked');
                    var dMsg = (checked) ? 'You want to activate the product?' :
                        'You want to deactivate the product?';
                    var value = checkbox.prop('value');
                    bootbox.confirm({
                        size: 'medium',
                        title: 'Product Activation & Deactivation',
                        message: dMsg,
                        callback: function (confirmed) {
                            if (confirmed) {
                                console.log(value);

                                var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
                                $.post(activationUrl, function (data) {
                                    bootbox.alert({
                                        size: 'medium',
                                        title: 'Information',
                                        message: data
                                    });
                                });
                            }
                            else {
                                checkbox.prop('checked', !checked);
                            }
                        }
                    });
                });

            }
        });
    }



    // -----
    // handling the click event of refresh cart button

    $('button[name="refreshCart"]').click(function() {

        // fetch the cart line id
        var cartLineId = $(this).attr('value');
        var countElement = $('#count_' + cartLineId);

        var originalCount = countElement.attr('value');
        var currentCount = countElement.val();

        // work only when the count has changed
        if(currentCount !== originalCount) {
            if(currentCount < 1 || currentCount > 3) {
                // reverting back to the original count
                // user has given value below 1 and above 3
                countElement.val(originalCount);
                bootbox.alert({
                    size: 'medium',
                    title: 'Error',
                    message: 'Product count should be minimum 1 and maximum 3!'
                });
            }
            else {
                var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
                // forward it to the controller
                window.location.href = updateUrl;
            }
        }
    });
    //-----------------

});

var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-isAccessPossible').on('click',function(){
            _this.isAccessPossible();
        });
        $('#btn-reply-save').on('click',function(){
            _this.replySave();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/record';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        var data ={
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val()

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/record';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function(){
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url:'/api/v1/posts/'+id
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href ='/record';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    isAccessPossible : function(){
        var data = {
            author: $('#author').text()
        };
        var id = $('#id').text();
        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            url:'/api/v1/isAccessPossible'
        }).done(function(result){
            console.log(result);
            if (result['flag']===true){
                window.location.href="/posts/update/"+id;
            }else{
                alert("접근권한이 없습니다.");
                window.location.href ="/posts/detailView/"+id;
            }
        })
    },
    replySave : function(){
        var data ={
            pageNum: $('#id').text(),
            author: $('#reply-author').text(),
            content: $('#reply-content').val()
        };
        console.log(data);
        alert("a");
        var id = $('#id').text();
        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            url:'/api/v1/replys'
        }).done(function(result){
            alert("댓글 등록 완료.");
            window.location.href ="/posts/detailView/"+id;
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }

};

main.init();
function showPicByName(PicName){
    axios({
        url:'http://1.117.80.52:8033/pic/photo/getSearchPics',
        method: 'GET',
        params: {
            picCategory: PicName
        }
    }).then(res =>{
        console.log(res)
        if(res.status==200){
            for(var i=0; i<res.data.data.length; i++){
                var new_photo_box_div = document.createElement('div');
                var new_photo_link_a = document.createElement('a');
                var new_photo_img = document.createElement('img');
                var new_photo_imgdiv = document.createElement('div');
                var new_photo_photosname_p = document.createElement('p');
                var new_photo_loadername_p = document.createElement('p');
                var new_photo_photoskind_p = document.createElement('p');
                new_photo_box_div.className = 'first_div';
                new_photo_imgdiv.className = 'second_div';
                new_photo_link_a.className = 'photos-a';
                
                new_photo_link_a.href = 'lookphotos.html?photo='+res.data.data[i]['url']+'='+res.data.data[i]['location']+'='+res.data.data[i]['id']+'='+res.data.data[i]['name']

                new_photo_link_a.className = 'the_a';
                new_photo_img.src = 'https://zjsl-1314394482.cos.ap-shanghai.myqcloud.com'+res.data.data[i]['url']
                new_photo_img.className = 'the_img';

                new_photo_photosname_p.innerText = res.data.data[i]['name']
                new_photo_loadername_p.innerText = res.data.data[i]['id']
                new_photo_photoskind_p.innerText = res.data.data[i]['location']

                new_photo_photosname_p.setAttribute('id','first_p');
                new_photo_loadername_p.className = 'second_p';
                new_photo_photoskind_p.className = 'third_p';

                new_photo_link_a.appendChild(new_photo_img);
                new_photo_imgdiv.appendChild(new_photo_link_a);
                new_photo_box_div.appendChild(new_photo_imgdiv);
                new_photo_box_div.appendChild(new_photo_photosname_p);
                new_photo_box_div.appendChild(new_photo_loadername_p);
                new_photo_box_div.appendChild(new_photo_photoskind_p);

                document.getElementById('photosbox').appendChild(new_photo_box_div);
            } 
        }
    })
}
function jumpToFour(place){
    container.style.display = 'none'
    $('.blank1').css('display','none')
    $('#pageBtnBox').css('display','none')
    $('.slogan').css('display','none')
    $('.info').css('display','none')
    $('.blank2').css('display','none')
    $('.blank3').css('display','none')
    $('#button000').css('display','none')
    fourOrNot=1
    header.className = "header-out"	 //显示按钮
    header_a01.className = "header-out-a"
    header_a02.className = "header-out-a" 
    header_a03.className = "header-out-a" 
    header_a04.className = "header-out-a" 
    header_a05.className = "header-out-a" 
    header_a06.className = "header-out-a" 
    photosbox.innerHTML = ''
    if(place=='ZDT'){
        document.getElementsByClassName('photos-window-top')[0].innerHTML = '<h3>浙东唐诗之路</h3>'
        showPicByName('浙东唐诗之路')
    }
    else if(place=='OJ'){
        document.getElementsByClassName('photos-window-top')[0].innerHTML = '<h3>瓯江山水诗路</h3>'
        showPicByName('瓯江山水诗路')
    }
    else if(place=='QTJ'){
        document.getElementsByClassName('photos-window-top')[0].innerHTML = '<h3>钱塘江诗路</h3>'
        showPicByName('钱塘江诗路')
    }
    else if(place=='DYH'){
        document.getElementsByClassName('photos-window-top')[0].innerHTML = '<h3>大运河诗路</h3>'
        showPicByName('大运河诗路')
    }
}
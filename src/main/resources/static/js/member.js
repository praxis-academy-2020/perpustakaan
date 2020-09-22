
//untuk memanggil semua data
 (async function list_member(){
  const member = await getMember();
  console.log(member);
  updateUI(member);
})();

async function getMember(){
  return await fetch('/member/')
          .then(response => response.json());
} 

async function getMemberId(id){
  return await fetch('/member/'+id)
          .then(response => response.json());
} 

function hapus(id){
  fetch('/member/'+id,{method : 'DELETE'});
}



function updateUI(member){
  let raw='';
  member.forEach(element => raw += showCards(element));
  const memberContainer = document.querySelector('.member-container');
  memberContainer.innerHTML=raw;
}

function showCards(element){
 return `<div class="col-md-3  my-2">
          <div class="card">
              <img src="" class="card-img-top">
              <div class="card-body">
              <center>
              <h5 class="card-title">Nama : ${element.nama}</h5>
              <h6 class="card-subtitle mb-2 text-muted">email : ${element.email}</h6>
              <h6 class="card-subtitle mb-2 text-muted">No Hp : ${element.noHp}</h6>
              <a href="#" data-id ="${element.id}" class="btn btn-primary btn-detail-member " data-toggle="modal" data-target="#edit-member-model" data-whatever="@mdo">Edit</a>
              <a href="#" data-id ="${element.id}" class="btn btn-primary btn-hapus-member">Hapus</a>
              </center>
              </div>
          </div>
        </div>`

}



document.addEventListener('click',function(e){
  
  //delete file
  if (e.target.classList.contains('btn-hapus-member')) {
   try{
     const id = e.target.dataset.id;
     console.log(id);
     fetch('/member/'+id,{method : 'DELETE'});
   }catch(e){
     alert(console.error());
   }finally{
    location.reload();
   }
  }
  if(e.target.classList.contains('btn-detail-member')){
    const id = e.target.dataset.id;
    console.log(id);
    //selanjutnya tampilkan edit
    editMember(id);
  }
  if (e.target.classList.contains('btn-save-edit-model')) {
    var form = document.querySelector("form");
    alert('form.elements')
  
    
  }
});


async function editMember(id){
  memberById = await getMemberId(id);
  console.log(memberById);
  const memberModalKonten = `<div class="modal-header">
                                <h4 class="modal-title" id="exampleModalLabel"><center><strong>${memberById.id}-${memberById.nama}</strong> </center></h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div class="modal-body">
                                  <form method = "PUT" >
                                      <div class="form-group">
                                        <label for="nama-name" class="col-form-label">Nama:</label>
                                        <input name = "nama" type="text" class="form-control" id="nama-name" value="${memberById.nama}">
                                      </div>
                                      <div class="form-group">
                                          <label for="email-name" class="col-form-label">Email:</label>
                                          <input type="text" class="form-control" id="email-name" value="${memberById.email}">
                                      </div>
                                        <div class="form-group">
                                            <label for="noHp-name" class="col-form-label">No Hp:</label>
                                            <input type="text" class="form-control" id="noHp-name" value= "${memberById.noHp}">
                                        </div>
                                      </div>
                                      <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary btn-save-edit-model">Simpan Perubahan</button>
                                      </div>
                                    </form>`
  $('.modal-content').html(memberModalKonten);
}


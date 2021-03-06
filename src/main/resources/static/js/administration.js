;(()=> {

  let mwUserDelete = document.querySelector('.modal_window--user_delete');
  let mwUserAdd = document.querySelector('.modal_window--add_user');
  mwUserDelete.dataset.state = "none";
  mwUserAdd.dataset.state = "none";

  let tableUsers = $('#users_table').DataTable({
    dom: 'Bfrtip',
    lengthChange: false,
    // pageLength: 2,
    oLanguage: {
      sSearch: "Поиск:"
    },
    columnDefs: [ {
      orderable: false,
      className: 'select-checkbox',
      targets: 0,
      width: "10%",
    },
      // {
      //   width: "15%",
      //   targets: 1,
      // },
      // {
      //   width: "15%",
      //   targets: 2,
      // }
    ],
    select: {
      style:    'os',
      selector: 'td:first-child'
    },
    order: [[ 1, 'asc' ]],
    buttons: [
      {
        text: 'Добавить',
        action: function ( e, dt, node, config ) {
          changeModalWindowState(mwUserAdd, 'run');
          $('body').addClass('modal-active');
        },
        className: 'MyBtnClass',
      },
      {
        text: 'Изменить',
        action: function ( e, dt, node, config ) {
          alert( 'Button activated' );
        },
        enabled: false,
        className: 'MyBtnClass',
      },
      {
        text: 'Удалить',
        action: function ( e, dt, node, config ) {
          changeModalWindowState(mwUserDelete, 'run');
          $('body').addClass('modal-active');
        },
        className: 'MyBtnClass',
        enabled: false,
      }
    ]
  });

  tableUsers.on( 'select deselect', function () {
    let selectedRows = tableUsers.rows( { selected: true } ).count();

    tableUsers.button( 1 ).enable( selectedRows === 1 );
    tableUsers.button( 2 ).enable( selectedRows > 0 );
  } );

  tableUsers.on( 'select', function ( e, dt, type, indexes ) {
    let selectedRow = $('.custom-selected');
    if( selectedRow !== null) {
      selectedRow.removeClass('custom-selected');
    }
    tableUsers[ type ]( indexes ).nodes().to$().addClass( 'custom-selected' );
  } );

  const changeModalWindowState = (mwDOM, state) => {
    void mwDOM.offsetWidth;
    mwDOM.dataset.state = state;
    $('body').removeClass('modal-active');
  };

  $('.accept_delete_user_btn').click((event)=> {
    let userId = $('.custom-selected').data('userId');
    $.ajax({
      url: `http://localhost:8080/user/${userId}`,
      type: 'DELETE',
      cache: false,
      crossDomain: true,
      crossOrigin: true,
      dataType: 'text',
      success: function (status) {
        if(status === '409') {
          mwUserDelete.dataset.state = 'away';
          $('body').removeClass('modal-active');
          $('.deleteUser_error_message').css('display', 'block');
        } else {
          changeModalWindowState(mwUserDelete, 'away');
          $('.custom-selected').remove();
          $('.deleteUser_error_message').css('display', 'none');
        }
      }

    });
    return false;
  });

  $('.close_add_user_mw_btn').click(() => {
    changeModalWindowState(mwUserAdd, 'away');
  });

  $('.close_delete_user_mw_btn').click(() => {
    changeModalWindowState(mwUserDelete, 'away');
  });

  $('.decline_delete_user_btn').click(() => {
    changeModalWindowState(mwUserDelete, 'away');
  });

  $('.user_login_field').focusout(function(event) {

    const userLogin = this.value;
    $.ajax({
      url: `http://localhost:8080/user/login/${userLogin}`,
      type: 'GET',
      cache: false,
      crossDomain: true,
      crossOrigin: true,
      dataType: 'json',
      success: function (user) {
        if (user.id !== 0) {
          // alert('yooooo');
          // $('.user_login_field').val("Логин уже существует");
          $('.addUser_error_message').css('display', 'block');
        } else {
          $('.addUser_error_message').css('display', 'none');
        }
      }

    })
  });


})();
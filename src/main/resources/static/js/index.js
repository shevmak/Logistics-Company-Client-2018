;(() => {
  $( document ).ready(function() {
    showViewPage('addOrder');
    changeBreadCrumbText('Добавить заказ');
    changeNavigationText('Заказ на загрузку');
  });

  const showViewPage = (name) => {
    $.ajax({
      url: name,
      cache: false,
      success: function(html) {
        console.log('sdfsdfds');
        let $doc = $('<div></div>').html(html);
        let $el = $doc.find(`.${name}_container`);
        $('.content').append($el);
      },
    });
  };

  const changeNavigationText = (text) => {
    $('.navigation_panel__text').text(text);
  };

  const changeBreadCrumbText = (text) => {
    $('.current_place_on_site').text(text);
  };

  const clearDynamicalField = () => {
    $('.content').html('');
  };

  // -------------EVENTS-------------------
  $('.site_menu__list').click((event) => {
    console.log('sdfsdf');
    let { target } = event;

    while (target != this) {
      if (target.classList.contains('site_menu_item')) {
        clearDynamicalField();
        showViewPage(target.dataset.action);

        switch (target.dataset.action) {
          case 'ordersRealization':
            changeNavigationText('Транспортные заказы');
            changeBreadCrumbText('Заказы в реализации');
            break;
          default: return;
        }
        return;
      }
      target= target.parentNode;
    }

  })



})();


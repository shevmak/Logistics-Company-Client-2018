package edu.bsuir.controller.notice;

import edu.bsuir.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Controller
public class NoticeAdd {

    @Value("${message.error}")
    private String messageError;

    @Value("${url.notice}")
    private String URL_NOTICE;

    @RequestMapping(value = {"/addNotice"}, method = RequestMethod.GET)
    public String showAddNoticePage(Model model) {

        Notices noticeForm = new Notices();
        model.addAttribute("noticeForm", noticeForm);

        return "addNotice";
    }

    @RequestMapping(value = {"/addNotice"}, method = RequestMethod.POST)
    public String saveNotice(Model model, @ModelAttribute("noticeForm") Notices noticeForm) {

        /* Start Setting Order */
        String orderOfLoading = noticeForm.getOrderOfLoading();
        Date dateOfLoading = noticeForm.getDateOfLoading();
        String typeOfRollingStock = noticeForm.getTypeOfRollingStock();
        String placeOfCustomsClearance = noticeForm.getPlaceOfCustomsClearance();
        String transportDocument = noticeForm.getTransportDocument();
        String status = "Не заполнена";

        Notices noticeToAdd = new Notices(orderOfLoading, dateOfLoading, typeOfRollingStock, placeOfCustomsClearance, transportDocument, status);
        /* End Setting Order */

        /* Start Setting Objects*/
        noticeToAdd.setDriver(noticeForm.getDriver());
        noticeToAdd.setForwarder(noticeForm.getForwarder());
        /* End Setting Objects*/

        /* Как я понял нужно ещё обновить заказ, то есть изменить у него статус и добавить id авизации */
        /* И сделать проверку на существование этого заказа. Мы добавляем его номер, а если его нету. Можно список выпадающий */

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Notices> requestBody = new HttpEntity<>(noticeToAdd);

        ResponseEntity<Notices> result = restTemplate.postForEntity(URL_NOTICE, requestBody, Notices.class);

        if (result.getStatusCode() == HttpStatus.OK) {
            noticeToAdd = result.getBody();
            if (noticeToAdd != null)
                return "redirect:/welcome";
        }

        model.addAttribute("errorMessage", messageError);
        return "addNotice";
    }
}

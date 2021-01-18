package library;

import library.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_CREATE_1 (@Payload Reserved reserved) {
        try {
            if (reserved.isMe()) {
                // view 객체 생성
                Mypage mypage  = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setMemberId(reserved.getMemberId());
                mypage.setRentalId(reserved.getId());
                mypage.setBookStatus(reserved.getBookStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);

// Mypage Create 되었단 메시지 출력..
System.out.println("##### listener Create : " + reserved.toJson());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRentaled_then_UPDATE_1(@Payload Rentaled rentaled) {
        try {
            if (rentaled.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByRentalId(rentaled.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setBookStatus(rentaled.getBookStatus());
                    mypage.setRentalId(rentaled.getId());
                    //mypage.setMemberId(rentaled.getMemberId());

                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);

// Mypage Update 되었단 메시지 출력..
System.out.println("##### listener UpdateStatus rentaled : " + rentaled.toJson());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelled_then_UPDATE_2(@Payload Cancelled cancelled) {
        try {
            if (cancelled.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByRentalId(cancelled.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setBookStatus(cancelled.getBookStatus());
                    mypage.setRentalId(cancelled.getId());
                    //mypage.setMemberId(cancelled.getMemberId()); // rentalId가 키값일 것임

                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
// Mypage Update 되었단 메시지 출력..
System.out.println("##### listener UpdateStatus cancelled : " + cancelled.toJson());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturned_then_UPDATE_3(@Payload Returned returned) {
        try {
            if (returned.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByRentalId(returned.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setBookStatus(returned.getBookStatus());
                    mypage.setRentalId(returned.getId());
                    //mypage.setMemberId(returned.getMemberId());

                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);

// Mypage Update 되었단 메시지 출력..
System.out.println("##### listener UpdateStatus returned : " + returned.toJson());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
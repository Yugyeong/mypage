package myProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import myProject.config.kafka.KafkaProcessor;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSigned_then_CREATE_1 (@Payload Signed signed) {
        try {
        	System.out.println("***********************");
        	System.out.println(signed.getStudentId());
            if (signed.isMe()) {
                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setStudentId(signed.getStudentId());
                mypage.setStatus(signed.getStatus());
                mypage.setStudentName(signed.getStudentName());
                mypage.setClassName(signed.getClassName());
               
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenSignApproved_then_UPDATE_1(@Payload SignApproved signApproved) {
        try {
            if (signApproved.isMe()) {
            	//System.out.println(signApproved.getId());
                // view 객체 조회
                //Optional<Mypage> mypageOptional = mypageRepository.findById(signApproved.getStudentId());
                /*if( mypageOptional.isPresent()) {
                    Mypage mypage = mypageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setId(signApproved.getStudentId());
                    mypage.setStatus(signApproved.getStatus());
                    mypage.setStudentName(signApproved.getStudentName());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }*/
            	
            	List<Mypage> mypageList = mypageRepository.findByStudentId(signApproved.getStudentId());
            	for(Mypage mypage: mypageList) {
            		mypage.setStudentId(signApproved.getStudentId());
            		mypage.setStatus(signApproved.getStatus());
            		mypage.setStudentName(signApproved.getStudentName());
            		mypage.setClassName(signApproved.getClassName());
            		
            		mypageRepository.save(mypage);
            	}

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceled_then_UPDATE_2(@Payload Canceled canceled) {
        try {
            if (canceled.isMe()) {
                /*// view 객체 조회
                Optional<Mypage> mypageOptional = mypageRepository.findById(canceled.getStudentId());
                if( mypageOptional.isPresent()) {
                    Mypage mypage = mypageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setId(canceled.getStudentId());
                    mypage.setStatus(canceled.getStatus());
                    mypage.setStudentName(canceled.getStudentName());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }*/
            	List<Mypage> mypageList = mypageRepository.findByStudentId(canceled.getStudentId());
            	for(Mypage mypage: mypageList) {
            		mypage.setStudentId(canceled.getStudentId());
            		mypage.setStatus(canceled.getStatus());
            		mypage.setStudentName(canceled.getStudentName());
            		mypage.setClassName(canceled.getClassName());
        		
            		mypageRepository.save(mypage);
            	}
        	
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceled_then_UPDATE_2(@Payload Changed changed) {
        try {
            if (changed.isMe()) {
                /*// view 객체 조회
                Optional<Mypage> mypageOptional = mypageRepository.findById(canceled.getStudentId());
                if( mypageOptional.isPresent()) {
                    Mypage mypage = mypageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setId(canceled.getStudentId());
                    mypage.setStatus(canceled.getStatus());
                    mypage.setStudentName(canceled.getStudentName());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }*/
            	List<Mypage> mypageList = mypageRepository.findByStudentId(changed.getStudentId());
            	for(Mypage mypage: mypageList) {
            		mypage.setStudentId(changed.getStudentId());
            		mypage.setStatus(changed.getStatus());
            		mypage.setStudentName(changed.getStudentName());
            		mypage.setClassName(changed.getClassName());
        		
            		mypageRepository.save(mypage);
            	}
        	
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
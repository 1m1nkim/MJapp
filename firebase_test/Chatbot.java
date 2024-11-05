package com.example.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chatbot extends AppCompatActivity {
    Button back_btn_chat;
    private float startY = 0f;
    private float initialY = 0f;
    private View layoutToMove;
    private NestedScrollView swipeLayout;
    private CoordinatorLayout mainLayout;
    private ScrollView chatScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot_main);
        back_btn_chat = findViewById(R.id.back_btn_chat);
        swipeLayout = findViewById(R.id.swipe);
        mainLayout = findViewById(R.id.cons_layout);
        chatScrollView = findViewById(R.id.chat_scroll_view);
        back_btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        swipeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();
                        initialY = swipeLayout.getY();
                        layoutToMove = swipeLayout;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (layoutToMove != null) {
                            float deltaY = event.getRawY() - startY;
                            if (deltaY > 0) {
                                float newY = initialY + deltaY;
                                layoutToMove.setY(newY);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        //chat_list에서 누른 text를 가져와 그에 맞는 답변
        for (int i = 1; i <= 6; i++) {
            int textViewId = getResources().getIdentifier("chat_text_" + i, "id", getPackageName());
            final TextView chatText = findViewById(textViewId);

            chatText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String chatMessage = chatText.getText().toString();

                    View senderView = LayoutInflater.from(Chatbot.this).inflate(R.layout.sender_view, null);
                    View receiverView = LayoutInflater.from(Chatbot.this).inflate(R.layout.receiver_view, null);
                    TextView senderText = senderView.findViewById(R.id.sender_text);
                    TextView senderTime = senderView.findViewById(R.id.sender_time);

                    TextView receiverText = receiverView.findViewById(R.id.receiver_text);
                    TextView receiverTime = receiverView.findViewById(R.id.receiver_time);
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String currentTime = sdf.format(new Date());
                    senderTime.setText(currentTime);
                    receiverTime.setText(currentTime);

                    if (chatMessage.equals("등록금 납부는 어디서 확인 할 수 있나요?")) {
                        receiverText.setText("학교홈페이지 I-CAMPUS 종합정보시스템에 접속하여 조회할 수 있습니다.\n" +
                                "I-CAMPUS > 등록(분납/고지서) > 등록금납부확인서 \n" +
                                "에서 조회가능하며 '등록금고지서'의 경우 등록금 납부기간에만 출력 및 납부 가능합니다.\n" +
                                "\n" +
                                "등록금 납부기간은 [학사일정]에서 확인하실 수 있으며, 해당기간에 [학사공지]를 확인하여주시기 바랍니다.");
                    } else if (chatMessage.equals("성적 확인은 어디서 확인 할 수 있나요?")) {
                        receiverText.setText("학교홈페이지 I-CAMPUS 종합정보시스템에 접속하여 조회할 수 있습니다.\n" +
                                "I-CAMPUS > 성적조회 \n" +
                                "에서 확인가능하며 '현재학기 성적'은 성적조회기간에만 확인가능합니다.\n" +
                                "\n" +
                                "성적조회기간은 [학사일정]에서 확인하실 수 있으며, 해당기간에 [학사공지]를 확인하여주시기 바랍니다.");
                    } else if (chatMessage.equals("휴학 및 자퇴 신청은 어디서 하나요?")) {
                        receiverText.setText("휴학절차 및 신청 방법\n" +
                                "▶일반휴학 - 당해학기 휴학기간 중 학과사무실 방문 > 학과장 면담 > 휴학원서 제출(본관204호: 학사지원팀)\n" +
                                "※ 학사내규 제23조④ 일반휴학은 ∼1학년 1학기 중에는 휴학할 수 없다. \n" +
                                "※ 학사내규 제23조⑤ 복학생 및 편입생은 당해 학기에는 휴학할 수 없다.\n" +
                                "\n" +
                                "▶질병휴학 - 4주이상 입원 및 통원치료를 요하는 내용이 기재된 1차 병원의 진단서를 지참하여 학과사무실 방문 > 학과장 면담 > 휴학원서 및 진단서 제출(본관204호: 학사지원팀)\n" +
                                "\n" +
                                "▶군휴학 - 입영일 2주 전부터 입영통지서 지참하여 학과사무실 방문 > 학과장 면담 > 휴학원서 및 입영통지서 제출(본관204호: 학사지원팀)\n" +
                                "\n" +
                                "▶창업휴학 - 사업자등록증, 재학증명서, 성적증명서(창업 관련 교과 이수 필), 창업기업확인서(중소벤처기업부 발급) 각 1부를 지참하여 진로취·창업팀(본관210호) 방문 -> 심의 절차(창업운영지원위원회)를 통한 창업사실확인서 발급 -> 당해학기 휴학기간(학사일정 참고) 중 학과사무실 방문 -> 학과장 면담 -> 휴학원서 및 창업사실확인서(사업자등록증, 재학증명서, 창업기업확인서 포함) 제출(본관204호 : 학사지원팀)\n" +
                                "※ 학사일정을 참고하여 휴학원서 접수기간 2주전까지 창업사실확인서 발급을 신청하시기 바랍니다.   \n" +
                                "\n" +
                                "▶자퇴 - 자퇴원서 및 사유서 교부(학과사무실) > 보호자 및 지도교수 등 확인날인(학과방문) > 자퇴원서 제출(본관204호: 학사지원팀)\n" +
                                "\n" +
                                "더 자세한 사항은 [메인홈페이지 > 학사서비스 > 학사안내 > 학적 > 휴학(제적/자퇴)] 에서 확인하실 수 있습니다.\n");
                    } else if (chatMessage.equals("휴학기간은 언제까지 가능한가요?")) {
                        receiverText.setText("▶일반휴학 - 휴학기간은 1년을 초과하지 못합니다. 휴학기간이 만료되어 휴학을 계속 원할 때에는 휴학기간 만료이전에 휴학연장원을 제출하여야 합니다. (2022학년도 이전 입학한 재적생의 경우 2년제 3회, 3년제 4회까지 가능하며, 2023학년도 신입생부터 1회만 가능합니다.)\n" +
                                "▶군휴학 -  휴학횟수에 포함하지 않으며,군휴학기간은 2년까지 가능합니다.\n" +
                                "▶창업휴학 - 창업휴학기간은 1년을 초과할 수 없으며, 2회에 한하여 허가할 수 있습니다. 창업휴학은 일반휴학횟수에 포함하지 않습니다.\n" +
                                "\n" +
                                "더 자세한 사항은 [메인홈페이지 > 학사서비스 > 학사안내 > 학적 > 휴학] 에서 확인하실 수 있습니다.");
                    } else if (chatMessage.equals("도서관 이용시간은 어떻게 되나요?")) {
                        receiverText.setText("▶자료열람실 운영시간\n" +
                                "-학기중: 평일 09:00~22:00 / 주말 및 공휴일 휴관\n" +
                                "-방학중: 평일 09:00~17:30 / 주말 및 공휴일 휴관\n" +
                                "▶자유열람실 운영시간\n" +
                                "-학기중: 평일 09:00~22:00 / 주말 및 공휴일 휴관\n" +
                                "-방학중: 미운영\n" +
                                "▶M-Street 운영시간\n" +
                                "-학기중: 평일 09:00~22:00 / 주말 및 공휴일 휴관\n" +
                                "-방학중: 평일 09:00~17:30 / 주말 및 공휴일 휴관\n" +
                                "\n" +
                                "더 자세한 사항은 '명지전문대학 LIBRARY' app을 통해 확인하실 수 있습니다.\n");
                    } else if (chatMessage.equals("출석인정 이의신청은 어디서 하나요?")) {
                        receiverText.setText("출석인정 가능 사유가 발생한 날로부터 6일 이내(법정공휴일 제외)에 출석인정신청서와 출석 대체 과제물을 첨부하여 전자출결시스템(UCheck Plus)에서 출결이의신청을 할 수 있습니다.(다만, 국가 의무 수행에 대해서는 대체 과제물을 부과하지 않음)\n" +
                                "\n" +
                                "더 자세한 사항은 [메인홈페이지 > 학사안내 > 수업 > 출석인정] 에서 확인하실 수 있습니다.");
                    }

                    senderText.setText(chatMessage);
                    addViewToChatScrollView(senderView);
                    addViewToChatScrollView(receiverView);
                }
            });
        }
    }
    // ScrollView에 새로운 뷰를 추가하는 메서드
    private void addViewToChatScrollView(View view) {
        LinearLayout chatContent = findViewById(R.id.chat_content);
        chatContent.addView(view);
        chatScrollView.post(new Runnable() {
            @Override
            public void run() {
                chatScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}

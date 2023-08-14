# Step2(2023-08-14) #
    * 틱택토(랜덤)
        * [x] 메뉴에서 2인을 선택하면 2인 모드로 전환한다.
        * [x] 메뉴에서 랜덤을 선택하면 랜덤 모드로 전환한다.
        * [x] 앱을 시작하면 랜덤 모드로 시작한다.
        * [x] 유저는 X를 표기하고 AI는 O를 표기한다.
        * [x] AI는 남은 칸 중에서 랜덤으로 표기한다.

# Step1(2023-08-13) #
    * 틱택토(2인)
        * [x] 앱을 시작하면 바로 틱택토 게임이 시작된다.
        * [x] X가 선수, O가 후수를 둔다.
        * [x] 유저가 직접 X, O를 표기하며 진행한다.
        * [x] 다시 시작하기 버튼을 누르면, 게임이 다시 시작된다.
        * [x] viewModel 테스트 코드 추가
        * [x] domain tictactoc 테스트 코드 추가
    * 1차 피드백
        * [x] TictactocToastMessage enum class message res 가지도록 수정
        * [x] TictactocToastMessage Event 래핑 추가
        * [x] View 에서 doamin Point 대신 TictactocCell import 하도록 수정
        * [x] 테스트 코드 assertEquals -> assertThat 수정
        * [x] 기존 map 객체 일급 컬렉션으로 수정
        * [x] viewModel 에서 값 검증하던 내용 수정
        * [x] viewModel board LiveData 값 수정( Array<Array<LiveData<Turn>>> -> LiveData<Array<Array<Turn>>> ) 
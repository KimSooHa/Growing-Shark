# 🦈 Growing-Shark - java mini game project

## 개요
- 프로젝트 명: Growing-Shark
- 개발 인원: 1명
- 개발 기간: 2개월
- 개발 목적
  - 요리 레시피를 공유하고 함께 요리하는 공간을 제공해 스터디를 모집하는 커뮤니티 사이트 제작
  - java 언어를 활용한 코딩 연습을 위한 물고기를 먹는 상어게임 스타일의 미니게임 프로젝트
  - 객체지향 프로그래밍에 중점을 둔 프로젝트로, 시각적 재미를 위해 Swing을 이용한 GUI로 구현
- 개발 환경: JDK 11, Swing(AWT)

## 패키지 구조
- src
    - game: 메인 메서드
    - item: 게임에 필요한 아이템 객체
        - background: canvas의 배경
        - button: button 인터페이스 및 button 관련 구현체들
        - fish: fish 인터페이스 및 물고기 구현체들
    - ui: 메인 frame 및 canvas 클래스들
- res
    - audio: 게임 배경음악 및 효과음
    - images: 게임 이미지들

## 내용

### 🎯게임의 목표
Player인 상어는 자신보다 작은 바닷속 물고기를 먹으면서 몸집을 키워 레벨 7까지 올리면 미션을 클리어합니다. 자신보다 큰 물고기나 상어와 닿이면 라이프가 줄어들고, 정해진 라이프가 다 없어지면 gameover가 됩니다.


### 게임의 특징
- 물고기를 먹지 않으면 life가 줄어듭니다.
- 플레이어보다 큰 물고기나 상어와 닿으면 life가 줄어듭니다.
- mission에 정해진 종류의 물고기를 정해진 개수만큼 먹으면 레벨업이 됩니다.

### 키 설명
| 행동 | 키 |
| --- | --- |
| 이동 | ← , →,  ↑, ↓ |

### 게임 진행
<aside>
💡 Growing Shark는 Level 1에서 Level 7까지 존재합니다.
Player가 각 레벨에서 정해진 미션을 완수하면서 미션 클리어하면 됩니다.
</aside>

### 시작 페이지

![GOMCAM 20220926_0102040980](https://github.com/KimSooHa/Growing-Shark/assets/81688625/7c3c5d0d-4bdf-49fb-ac73-9fa6ecbcb14b)

🖱️ 시작 페이지 입니다. 마우스로 play 버튼을 클릭하면 게임화면으로 넘어갑니다.

### 게임 시작 화면

![GOMCAM 20220926_0106150924](https://github.com/KimSooHa/Growing-Shark/assets/81688625/21f9c259-2379-4693-8b7f-2d2613552741)

▶️ 게임 화면입니다. 게임이 시작되면 하단에 현재 상태바가 나오며, Level1부터 정해진 미션을 수행하면 됩니다.

🐠 정해진 미션이 물고기 아래 숫자는 먹어야 할 갯수이며, 해당 물고기를 먹을때마다 미션까지 남은 물고기수가 줄어듭니다.

### 라이프 감소

![GOMCAM 20220926_0106570265](https://github.com/KimSooHa/Growing-Shark/assets/81688625/c9c76d96-9765-44a1-a195-bf653d7ad6ba)

미션에 숫자가 적히지 않은 물고기는 플레이어보다 크기 때문에 닿으면 라이프가 감소하게 됩니다.


### 레벨업 및 물고기 종류 추가

![GOMCAM 20221004_0542440101](https://github.com/KimSooHa/Growing-Shark/assets/81688625/070e103e-58d1-4fdd-b04e-fc0050bc3ab7)

- 레벨이 올라갈수록 물고기 종류가 많아지고 먹을 수 있는 물고기 수도 많아지게 됩니다.
- 레벨업이 되면 플레이어의 크기가 커집니다.


### 게임 오버

![GOMCAM 20221004_0546310580](https://github.com/KimSooHa/Growing-Shark/assets/81688625/dab23aa0-9e14-4796-916f-8afbc263872a)

라이프가 다 닳아서 죽으면 게임오버 화면으로 넘어갑니다.

- 게임오버 화면에서 재시작 혹은 종료 중 마우스로 선택할 수 있습니다.
- 게임을 재시작하려면 Replay를 클릭해 다시 게임화면으로 넘어갑니다.
- 게임 replay는 level1부터 재시작합니다.


### 미션 클리어

![GOMCAM 20221004_0559480897](https://github.com/KimSooHa/Growing-Shark/assets/81688625/46fa5a60-36bf-44a9-97cb-626e8d5f12ac)

Level7까지 미션 완수하면 모든 미션을 클리어하고 게임이 종료됩니다.

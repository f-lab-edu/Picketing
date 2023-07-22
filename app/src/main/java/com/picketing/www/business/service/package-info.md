```java
package com.picketing.www.business.service;
```

# 목적
presentation 계층에서 사용하게 될 도메인의 usecase

# 제한
이 패키지에는 작성되면 안되는 제한을 설명합니다
* 도메인 핵심 로직이 작성되면 안됩니다.
* 리턴하거나 요청을 받는 로직에서는 `변환 계층*`이 포함되어야 합니다.

# 추가 설명
### 변환 계층
* 메시지를 수신하는 경우 Dto -> Domain
* 메시지를 송신하는 경우 Domain -> Dto

외부로 의존성을 보내지 않기 위함이며 내부에서는 오로지 도메인으로만 로직을 동작시키기 위함입니다.

내부에서는 도메인끼리 유기적으로 연결되어 비즈니스가 동작 하기 때문에, 외부 Dto 가 도메인 사이에 끼워지면 비즈니스 로직
은 변환 계층을 포함하여 더욱 복잡 해 질 것입니다. 따라서 확실한 변환 계층을 Service 에 포함하여 작성하는 것을 강제합니
다.
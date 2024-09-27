# 1. tasks.json파일 작성법

- F1을 누르고 Tasks: Configure Task를 찾아 선택
- Create tasks.json file from template
- MSBuild 선택

## 1-1. Window 사용자

- chrome.exe 파일의 위치를 보고 Program Files인지 Program Files (x86)인지 확인할 것
  - {
        "version": "2.0.0",
        "tasks": [
            {
                "label": "Chrome",
                "type": "process",
                "command": "chrome.exe",
                "windows": {
                    "command": "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"
                },
                "args": [
                    "${file}"
                ],
                "problemMatcher": [],
                "group": {
                    "kind": "build",
                    "isDefault": true
                }
            }
        ]
    }

## 1-2. Mac 사용자

- Mac의 경로를 잘 확인해서 chrome 경로 사용할 것
  - {
        "version": "2.0.0",
        "tasks": [
            {
                "label": "Chrome",
                "type": "process",
                "command": "chrome.exe",
                "osx": {
                    "command": "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
                },
                "args": [
                    "${file}"
                ],
                "problemMatcher": [],
                "group": {
                    "kind": "build",
                    "isDefault": true
                }
            }
        ]
    }
- 1번 방식으로 실행 할려면 

  ```
  ctrl + shift + b
  ```
  를 누르면 된다.

# 2. MARKETPLACE 활용

- marketplace 창 열기

  ```
  ctrl + shift + x
  ```

- open in browser 클릭 해서 install

- web browser에서 실행

  ```
  alt + b
  ```

  

# 3. Emmet 사용법

- 관련 링크
  - [Emmet 문법 사이트](https://docs.emmet.io/cheat-sheet/)
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Layout from "./layouts/Layout";
import Main from "./pages/Main";
import MyPage from "./pages/MyPage";
import Login from "./pages/Login";
import Error from "./pages/Error";

function App() {
  return (
    <BrowserRouter> 
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route index element={<Main/>}/>
          <Route path="main" element={<Main/>}/>
          <Route path="mypage" element={<MyPage/>}/>
          <Route path="login" element={<Login/>}/>
        </Route>
        <Route path="*" element={<Error/>}/>      {/*404에러와 관련된 페이지 추가*/}
      </Routes>
    </BrowserRouter>
  );
}

export default App;

// import { useState } from 'react';
// import { Toaster } from 'react-hot-toast';
// import { BrowserRouter, Route, Routes } from 'react-router-dom';
// import DashBoard from './Dashboard/DashBoard';
import './output.css';
// import LandingPage from './components/LandingPage';
// import AboutPage from './components/AboutPage';
// import RegisterPage from './components/RegisterPage';
// import LoginPage from './components/LoginPage';
// import ErrorPage from './components/ErrorPage';
// import Navbar from './components/NavBar';
// import Footer from './components/Footer';


// // npx @tailwindcss/cli -i ./src/index.css -o ./src/output.css --watch


// function App() {
//   const [count, setCount] = useState(0)

//   return (
//     <>
//       <BrowserRouter>
//         <Navbar />
//         <Toaster position='top center' />
//         <Routes>
//           <Route path="/" element={ <LandingPage/>} />
//           <Route path="/about" element={ <AboutPage/>} />
//           <Route path="/register" element={ <RegisterPage/>} />
//           <Route path="/login" element={ <LoginPage/>} />
//           <Route path="/dashboard" element={ <DashBoard/>} />
//           <Route path="/error" element={ <ErrorPage/>} />
//         </Routes>
//         <Footer/>
//       </BrowserRouter>
//     </>
//   );
// }

// export default App


import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import { getApps } from "./utils/helper";

function App() {
  const CurrentApp = getApps();

  return (
    <Router>
      <CurrentApp />
    </Router>
  );
}

export default App;
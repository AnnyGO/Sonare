import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import Cursos from "./components/pages/cursos/cursos";
import Home from "./components/pages/home"
import Turmas from "./components/pages/turmas";

export const Router = createBrowserRouter([
    {
        path:"/",
        element: <App/>,
        children: [
           
            {
                path:"/",
                element: <Home/>
            },
            {
                path:"/Cursos",
                element: <Cursos/>
            },
            {
                path:"/Turmas",
                element: <Turmas/>
            }
        ]
    }
])
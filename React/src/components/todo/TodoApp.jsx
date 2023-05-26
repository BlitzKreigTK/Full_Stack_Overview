import './TodoApp.css';
import 'bootstrap/dist/css/bootstrap.css';
import LoginComponent from './LoginComponent';
import WelcomeComponent from './WelcomeComponent';
import TodosListComponent from './TodosListComponent';
import HeaderComponent from './HeaderComponent';
import FooterComponent from './FooterComponent';
import ErrorComponent from './ErrorComponent';
import LogoutComponent from './LogoutComponent';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import AuthProvider, { useAuth } from './security/AuthContext';
import TodoComponent from './TodoComponent';

// AuthenticatedRoute for each children component check if user authenticated else return to "/" url
function AuthenticatedRoute({children}){
    const authContext = useAuth()
    if(authContext.isAuthenticated)
    {
        return children
    }
    return <Navigate to="/"/>
}

export default function TodoApp(){
    return(
        <div>
            <AuthProvider>
                <BrowserRouter>
                    <HeaderComponent/>
                    <Routes>
                        <Route path="/" element={<LoginComponent/>}/>
                        <Route path="/login" element={<LoginComponent/>}/>
                        <Route path="/welcome/:userName" element={
                        <AuthenticatedRoute>
                            <WelcomeComponent/>
                        </AuthenticatedRoute>
                        }/>
                        <Route path='/todosList' element={
                        <AuthenticatedRoute>
                            <TodosListComponent/>
                        </AuthenticatedRoute>
                        }/>
                        <Route path='/todo/:id' element={
                        <AuthenticatedRoute>
                            <TodoComponent/>
                        </AuthenticatedRoute>
                        }/>
                        <Route path='/logout' element={
                        <AuthenticatedRoute>   
                            <LogoutComponent/>
                        </AuthenticatedRoute>     
                        }/>
                        <Route path='*' element={
                        <AuthenticatedRoute>    
                            <ErrorComponent/>
                        </AuthenticatedRoute>    
                        }/>
                    </Routes>
                    <FooterComponent/>
                </BrowserRouter>
            </AuthProvider>
        </div>
    )
}
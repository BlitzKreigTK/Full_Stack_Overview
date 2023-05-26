import { useEffect, useState } from "react"
import { deleteTodoByIdApi, retrieveAllListOfTodosApi } from "./restApi/CallingAxiosApi"
import {useAuth} from './security/AuthContext';
import {useNavigate} from 'react-router-dom';

function TodosListComponent(){

    const[todos, setTodos] = useState([])
    const[message, setMessage] = useState(null)
    const authContext = useAuth()
    const userName = authContext.userName
    const naviagte = useNavigate()
    // eslint-disable-next-line
    useEffect(() => refreshTodos(), [])

    function refreshTodos(){
        retrieveAllListOfTodosApi('tulsi')
            .then(response => setTodos(response.data))
            .catch(error => console.log(error))
            .finally("Todos List called")
    }

    function deleteTodo(id){
        deleteTodoByIdApi(userName, id)
            .then(() => { setMessage(`Deleted todo by ${id} successfully`); refreshTodos();})
            .catch(error => console.log(error))
    }

    function updateTodo(id){
        naviagte(`/todo/${id}`)
    }

    function addNewTodo(){
        // redirects to update todo page or api (id: -1)
        naviagte(`/todo/-1`)
    }

    /*
    const today = new Date()
    const futureDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())
    const todos = [{id: 1, description: "Learn Azure", done: false, targetDate: futureDate}, 
                    {id: 2, description: "Learn AWS", done: false, targetDate: futureDate}, 
                    {id: 3, description: "Learn DevOps", done: false, targetDate: futureDate}]*/
    
    return(
        <div className="container">
            <h1>Todos List!</h1>
            {message && <div className="alert alert-warning">{message}</div>}
            <table className="table">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Is Done?</th>
                        <th>Target Date</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map(todo => 
                            <tr key={todo.id}>
                                <td>{todo.description}</td>
                                <td>{todo.done.toString()}</td>
                                <td>{todo.targetDate.toString()}</td>
                                <td><button className="btn btn-success" onClick={() => updateTodo(todo.id)}>Update</button></td>
                                <td><button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}>Delete</button></td>
                            </tr>    
                        ) 
                    }
                </tbody>
            </table>
            <div><button className="btn btn-success m-5" onClick={addNewTodo}>Add New Todo</button></div>
        </div>
    )
}

export default TodosListComponent
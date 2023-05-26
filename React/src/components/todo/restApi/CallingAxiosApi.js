import { apiClient } from "./axios"

export const retrieveHelloWorldPathVarBeanApi = (username) => apiClient.get(`/hello/path-variable/${username}`)

export const retrieveAllListOfTodosApi = (username) => apiClient.get(`/users/${username}/todos`)

export const findTodoByIdApi = (username, id) => apiClient.get(`/users/${username}/todos/${id}`)

export const deleteTodoByIdApi = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)

export const updateTodoByIdApi = (id, username, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)

export const createNewTodoByIdApi = (username, todo) => apiClient.post(`/users/${username}/todos`, todo)

export const JwtAuthApi = (username, password) => apiClient.post(`/authenticate`, { username, password })

/*export const basicAuthApi = (token) => apiClient.get(`/basicAuth`, { headers: { 'Authorization': token } })

export const retrieveHelloWorldBean = () => apiClient.get('/hello-world-bean', headers)
export function retrieveHelloWorldBean(){
    var username = 'tulsi'; 
    var password = 'tulsi';
    var credentials = btoa(username + ':' + password);
    var basicAuth = 'Basic ' + credentials;
    return axios.get('http://localhost:8080/hello-world-bean', { headers: { 'Authorization': basicAuth } })
}*/
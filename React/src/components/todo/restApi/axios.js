import axios from "axios";

// Axios provide create method to configure baseUrl 
export const apiClient = axios.create({baseURL: "http://localhost:8080"})
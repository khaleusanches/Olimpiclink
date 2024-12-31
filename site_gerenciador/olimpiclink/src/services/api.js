import axios from "axios"

const api = axios.create({
    baseURL: 'http://192.168.0.158:5000',
    headers: {
        'ngrok-skip-browser-warning': 'true', // Para ignorar o aviso do ngrok
    }
})

export default api;
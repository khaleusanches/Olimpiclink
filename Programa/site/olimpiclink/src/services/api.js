import axios from "axios"

const api = axios.create({
    baseURL: 'https://3675-189-29-146-118.ngrok-free.app/',
    headers: {
        'ngrok-skip-browser-warning': 'true', // Para ignorar o aviso do ngrok
    }
})

export default api;
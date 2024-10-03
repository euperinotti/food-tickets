import axios, { InternalAxiosRequestConfig } from "axios";

const URL = "http://localhost:8080";

const api = axios.create({
  baseURL: URL,
});

api.interceptors.request.use(
  (config: InternalAxiosRequestConfig<any>) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.code == "ERR_NETWORK") {
      error.message = "Não foi possível se conectar a API";
    }

    return Promise.reject(error);
  }
);

export default api;

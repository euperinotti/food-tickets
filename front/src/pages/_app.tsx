import { ToastProvider } from "@/context/ToastContext";
import type { AppProps } from "next/app";
import "../app/globals.css";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <ToastProvider>
      <Component {...pageProps} />
    </ToastProvider>
  );
}

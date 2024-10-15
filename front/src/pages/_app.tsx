import { ThemeProvider } from "@/components/shadcn/theme-provider";
import { ToastProvider } from "@/context/ToastContext";
import type { AppProps } from "next/app";
import "../styles/globals.css";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <ThemeProvider defaultTheme="light" storageKey='vite-ui-theme'>
      <ToastProvider>
        <Component {...pageProps} />
      </ToastProvider>
    </ThemeProvider>
  );
}

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { ContextProvider } from './Components/ContextApi.jsx'
import './index.css'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

const queryClint = new QueryClient();

createRoot(document.getElementById("root")).render(
  // <StrictMode>
    <QueryClientProvider client={queryClint}>
      <ContextProvider>
        <App />
      </ContextProvider>
    </QueryClientProvider>
  // </StrictMode>
);

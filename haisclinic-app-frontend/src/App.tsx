import { ToastProvider } from './components/Toast'
import { AppRoutes } from './routes/AppRoutes'

function App() {
  return (
    <ToastProvider>
      <AppRoutes />
    </ToastProvider>
  )
}

export default App

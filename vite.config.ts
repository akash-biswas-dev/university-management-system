import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import tailwindcss from '@tailwindcss/vite'


// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react(),
    tailwindcss()
  ],
  server: {
    proxy: {
      '/backend': {
        target: 'http://localhost:8501',
        changeOrigin: true,
      }
    }
  },
  build: {
    outDir: 'server/src/main/resources/static',  
    assetsDir: 'assets'
  }
})

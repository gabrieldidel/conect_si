
const ENVIRONMENT = 'dev';

const API_BASE_URL =
  ENVIRONMENT === 'local'
    ? 'http://localhost:8080'
    : 'https://conectsi-production.up.railway.app';

// Exporta para outros scripts
export { API_BASE_URL };

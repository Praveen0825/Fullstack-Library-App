export const oktaConfig = {
    clientId: '0oaa30zkfsypN3RIK5d7',
    issuer: 'https://dev-68343599.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}
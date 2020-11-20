export class AuthApi {
  login(usermame: string, password: string): Promise<any> {
    return fetch('/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({usermame, password})
    })
      .then(response => response.json());
  }
}
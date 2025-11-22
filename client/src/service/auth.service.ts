import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private http = inject(HttpClient);
    private readonly API_URL = '/api/v1/auth';

    signIn(username: string, password: string, rememberMe: boolean = false) {
        return this.http.post<{accessToken: string, refreshToken: string}>(this.API_URL, { username, password }, {
            params: { rememberMe: rememberMe.toString() }
        }).pipe(
            tap(response => {
                this.saveTokens(response.accessToken, response.refreshToken);
            })
        );
    }

    refreshToken() {
        const refreshToken = this.getRefreshToken();
        return this.http.post<string>(`${this.API_URL}/refresh-token`, {}, {
            headers: {
                'X-Refresh-Token': refreshToken || ''
            }
        }).pipe(
            tap(accessToken => {
                this.saveTokens(accessToken, refreshToken!);
            })
        );
    }

    saveTokens(accessToken: string, refreshToken: string) {
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
    }

    getAccessToken() {
        return localStorage.getItem('accessToken');
    }

    getRefreshToken() {
        return localStorage.getItem('refreshToken');
    }

    clearTokens() {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
    }
}
import { HttpErrorResponse, HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { catchError, switchMap, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
    const authService = inject(AuthService);
    const accessToken = authService.getAccessToken();

    let authReq = req;
    if (accessToken) {
        authReq = req.clone({
            setHeaders: {
                Authorization: `Bearer ${accessToken}`
            }
        });
    }

    return next(authReq).pipe(
        catchError((error: HttpErrorResponse) => {
            if (error.status === 401 && !req.url.includes('/auth/refresh-token')) {
                return authService.refreshToken().pipe(
                    switchMap((token:string) => {
                        const newAccessToken = token;
                        const newReq = req.clone({
                            setHeaders: {
                                Authorization: `Bearer ${newAccessToken}`
                            }
                        });
                        return next(newReq);
                    }),
                    catchError((refreshError) => {
                        authService.clearTokens();
                        return throwError(() => refreshError);
                    })
                );
            }
            return throwError(() => error);
        })
    );
};

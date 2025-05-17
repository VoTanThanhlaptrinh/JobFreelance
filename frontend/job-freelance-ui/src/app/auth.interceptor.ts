import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const jwtToken = localStorage.getItem('token');

  // Các API không cần token
  const noAuthUrls = ['/login', '/register','/isLogin'];

  // Nếu URL nằm trong danh sách không cần JWT, thì bỏ qua
  if (noAuthUrls.some(url => req.url.includes(url))) {
    return next(req);
  }

  if (jwtToken && jwtToken.split('.').length === 3) {
    const clone = req.clone({
      setHeaders: {
        Authorization: `Bearer ${jwtToken}` // ✅ Gắn đúng định dạng
      }
    });
    return next(clone);
  }

  return next(req);
};

import { Routes } from '@angular/router';
import { SignupComponent } from './modules/auth/signup/signup.component';
import { LoginComponent } from './modules/auth/login/login.component';

export const routes: Routes = [
    {path: "signup", component: SignupComponent},
    {path: "login", component: LoginComponent},
    {path: "user", loadChildren: () => import('./modules/user/user.module').then(e => e.UserModule)},
    {path: "admin", loadChildren: () => import('./modules/admin/admin.module').then(e => e.AdminModule)}
];

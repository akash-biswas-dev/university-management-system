import { Component, inject, signal, WritableSignal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ArrowRight, CircleAlert, Lock, LucideAngularModule, User } from 'lucide-angular';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Button } from '../../components/button/button.compoenent';
import { Input } from '../../components/input/input.component';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'auth-page',
  imports: [
    RouterLink,
    LucideAngularModule,
    ReactiveFormsModule,
    Button,
    Input
  ],
  templateUrl: 'auth.page.html'
})
export class AuthPage {
  private authService = inject(AuthService);

  protected readonly CircleAlert = CircleAlert;
  protected readonly ArrowRight = ArrowRight;
  protected readonly User = User;
  protected readonly Lock = Lock;

  protected errors: WritableSignal<SignInErrorType> = signal<SignInErrorType>({});
  protected isLoading: WritableSignal<boolean> = signal<boolean>(false);


  signInFormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    rememberMe: new FormControl(),
  })


  onSubmit() {
    if (this.signInFormGroup.valid) {
      this.isLoading.set(true);
      const { username, password, rememberMe } = this.signInFormGroup.value;
      this.authService.signIn(username, password, rememberMe).subscribe({
        next: () => {
          this.isLoading.set(false);
          // Navigate to home or dashboard
        },
        error: (err) => {
          this.isLoading.set(false);
          this.errors.set({ general: 'Invalid credentials' });
        }
      });
    }
  }

}

export type SignInErrorType = {
  general?: string;
  username?: string;
  password?: string;
}

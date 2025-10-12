import {Component, signal, WritableSignal} from '@angular/core';
import {RouterLink} from '@angular/router';
import {ArrowRight, CircleAlert, Lock, LucideAngularModule, User} from 'lucide-angular';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {Button} from '../../components/button/button.compoenent';
import {Input} from '../../components/input/input.component';


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

  protected readonly CircleAlert = CircleAlert;
  protected readonly ArrowRight = ArrowRight;
  protected readonly User = User;
  protected readonly Lock = Lock;

  protected errors: WritableSignal<SignInErrorType> = signal<SignInErrorType>({});
  protected isLoading:WritableSignal<boolean> = signal<boolean>(false);


  signInFormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    rememberMe: new FormControl(),
  })


  onSubmit(){
    console.log(this.signInFormGroup.value);
  }

}

export type SignInErrorType= {
  general?:string;
  username?:string;
  password?:string;
}

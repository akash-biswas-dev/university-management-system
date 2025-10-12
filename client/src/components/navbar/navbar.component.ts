import {Component, HostBinding, HostListener, signal, WritableSignal} from '@angular/core';
import {GraduationCap, X, LucideAngularModule, Menu} from 'lucide-angular';
import {RouterLink} from '@angular/router';
import {Button} from '../button/button.compoenent';


@Component({
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  imports: [
    LucideAngularModule,
    RouterLink,
    Button
  ],
  standalone: true
})
export class NavbarComponent {
  // Icons
  protected readonly GraduationCap = GraduationCap;
  protected readonly X = X;
  protected readonly Menu = Menu;

  // State Variables
  protected isOpenMobileMenu:WritableSignal<boolean> = signal<boolean>(false);
  protected isScrolled:WritableSignal<boolean> = signal<boolean>(false);

  // Capture the scroll event.
  private scrollTimeout:NodeJS.Timeout |null = null;

  @HostListener('window:scroll', [])
  onScrollEvent(){

    console.log(window.scrollY);
    console.log('Scrolled');
    this.isScrolled.set(true);

    if(this.scrollTimeout){
      clearTimeout(this.scrollTimeout);
    }

    this.scrollTimeout = setTimeout(() => {
        this.isScrolled.set(true);
    })
  }

  onMobileMenuClick() {
    this.isOpenMobileMenu.update(pre=>!pre);
  }

}

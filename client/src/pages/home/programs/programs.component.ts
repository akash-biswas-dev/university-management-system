import {Component} from '@angular/core';
import {ArrowRight, Award, BookOpen, Globe, LucideAngularModule, LucideIconData, Users} from 'lucide-angular';


@Component({
  selector:'home-programs',
  templateUrl:'programs.component.html',
  standalone:true,
  imports:[LucideAngularModule]
})
export class Programs{


  programs:ProgramsType[] = [
    {
      name: "Computer Science & Engineering",
      description:
        "Cutting-edge curriculum in AI, ML, and software development",
      students: "2,500+",
      icon: BookOpen,
    },
    {
      name: "Business Administration",
      description:
        "Comprehensive business education with real-world applications",
      students: "1,800+",
      icon: Users
    },
    {
      name: "Engineering & Technology",
      description: "Advanced engineering programs across multiple disciplines",
      students: "3,200+",
      icon: Award,
    },
    {
      name: "Liberal Arts & Sciences",
      description:
        "Diverse programs fostering critical thinking and creativity",
      students: "1,200+",
      icon: Globe
    },
  ];

  protected readonly ArrowRight = ArrowRight;
}

export type ProgramsType = {
  name: string;
  description: string;
  students: string;
  icon:LucideIconData
}

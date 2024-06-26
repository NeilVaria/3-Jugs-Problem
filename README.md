<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/neilvaria/3-Jugs-Problem">
    <img src="https://github.com/NeilVaria/3-Jugs-Problem/assets/60001894/f381699d-8711-4876-af71-e9239c845cc8" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">3 Jugs Problem Depth First Search</h3>

  <p align="center">
    Project that generates all possible nodes to solve the 3 jugs problem algorithmically using a depth first search given 3 jug capacities
  </p>
</div>


<!-- ABOUT THE PROJECT -->
## About The Project

This is a Java project delivered as a University assignment. It uses a depth first seach to algorithmically generate a list of all possible volumes of water in 3 jugs with user defined capacities.

#### What is the 3 Jug Problem?

The 3 jug problem typically involves three jugs with user-defined capacities. The objective is to measure a specific quantity of water using these jugs. The challenge lies in determining the series of steps required to achieve this goal, given that the jugs can only be filled completely, emptied completely, or poured from one jug to another until one of the jugs is either full or empty.

#### Die hard 3

A famous depiction of a similar problem appears in Die Hard 3, where they must transfer the correct amount of water into two jugs to defuse a bomb:

<p align="center">
  <a href="https://www.youtube.com/watch?v=BVtQNK_ZUJg&t=5s" title="Die hard 3 : Jugs Problem">
    <img src="https://github.com/NeilVaria/3-Jugs-Problem/assets/60001894/78af3e19-ba88-4126-a24e-f45a48c03f4d" alt="Die hard 3 : Jugs Problem">
  </a>
</p>

#### How It Works

The project uses a combination of algorithmic techniques to generate and explore all possible states of the jugs. Each state is represented by a triplet (a, b, c), where 'a', 'b', and 'c' represent the current amount of water in each jug. The system explores all possible transitions from one state to another by:

1. Filling any of the jugs to its maximum capacity.
2. Emptying any of the jugs completely.
3. Pouring water from one jug into another until either the source jug is empty or the destination jug is full.

By systematically applying these transitions and tracking the states visited, the project generates a complete set of distinct states that can be achieved.

In this implementation, the program allows users to input the capacities of the three jugs and then generates all distinct states using a depth-first search (DFS) approach, ensuring that all possible configurations are explored and recorded.


<!-- GETTING STARTED -->
## Getting Started

1. Clone the repo
   ```sh
   git clone https://github.com/neilvaria/3-Jugs-Problem.git
   ```
2. Complie the java code
   ```sh
   javac project.java
   ```
3. Run the java code
   ```sh
   java project.java
   ```



<!-- USAGE EXAMPLES -->
## Usage

To use the program, follow the onscreen prompts:
- Enter Capacity of First Jug: *Enter your input then press the enter key*
- Enter Capacity of Second Jug: *Enter your input then press the enter key*
- Enter Capacity of Third Jug: *Enter your input then press the enter key*

A set of distinct combinations will written to the console.

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Neil Varia - neilvaria@gmail.com

Project Link: [https://github.com/neilvaria/3-Jugs-Problem](https://github.com/neilvaria/3-Jugs-Problem)

<p align="right">(<a href="#readme-top">back to top</a>)</p>





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/neilvaria/3-Jugs-Problem.svg?style=for-the-badge
[contributors-url]: https://github.com/neilvaria/3-Jugs-Problem/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/neilvaria/3-Jugs-Problem.svg?style=for-the-badge
[forks-url]: https://github.com/neilvaria/3-Jugs-Problem/network/members
[stars-shield]: https://img.shields.io/github/stars/neilvaria/3-Jugs-Problem.svg?style=for-the-badge
[stars-url]: https://github.com/neilvaria/3-Jugs-Problem/stargazers
[issues-shield]: https://img.shields.io/github/issues/neilvaria/3-Jugs-Problem.svg?style=for-the-badge
[issues-url]: https://github.com/neilvaria/3-Jugs-Problem/issues
[license-shield]: https://img.shields.io/github/license/neilvaria/3-Jugs-Problem.svg?style=for-the-badge
[license-url]: https://github.com/neilvaria/3-Jugs-Problem/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/neilvaria
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 

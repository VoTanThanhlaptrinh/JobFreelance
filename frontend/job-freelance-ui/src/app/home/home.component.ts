import {AfterViewInit, Component, OnInit} from '@angular/core';
declare const Stickyfill: any;
declare var $: any;
import Quill from 'quill';
import {JobServiceService} from '../service/job-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {param} from 'jquery';


@Component({
  selector: 'app-home',
  imports: [
  ],
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements AfterViewInit, OnInit{
  goDetails(id: any) {
      this.acRoute.routeConfig
  }
  initPage:number = 0;
  newestJob: any = [];

  constructor(private jobService:JobServiceService, private acRoute: ActivatedRoute) {

  }
  ngOnInit(): void {
      this.loadNewstJob();
  }
  loadNewstJob() {
    this.jobService.getNDataJobNewest(this.initPage).subscribe({
      next: (res) =>{
        this.newestJob = res.data.content.map((item: any) => ({
          id : item.id,
          title: item.title,
          rangeSalary: item.rangeSalary,
          deadlineCV: item.deadlineCV,
        }));
        console.log(res.data.content);
      },
      error: (error) =>{
          console.log(error.message)
      }
    })
  }
  ngAfterViewInit(): void {
    this.initLoader();
    this.siteMenuClone();
    this.siteIsotope();
    this.fancyBoxInit();
    this.stickyFillInit();
    this.onePageNavigation();
    this.counterInit();
    this.selectPickerInit();
    this.owlCarouselInit();
    this.quillInit();
  }

  initLoader() {
    $('.loader').delay(1000).fadeOut('slow');
    $('#overlayer').delay(1000).fadeOut('slow');
  }

  siteMenuClone() {
    $('.js-clone-nav').each(function (this: Element) {
      const $this = $(this);
      $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
    });

    setTimeout(function () {
      let counter = 0;
      $('.site-mobile-menu .has-children').each(function (this: Element) {
        const $this = $(this);
        $this.prepend('<span class="arrow-collapse collapsed"></span>');
        $this.find('.arrow-collapse').attr({
          'data-toggle': 'collapse',
          'data-target': '#collapseItem' + counter,
        });
        $this.find('> ul').attr({
          'class': 'collapse',
          'id': 'collapseItem' + counter,
        });
        counter++;
      });
    }, 1000);

    $('body').on('click', '.arrow-collapse', function (this: Element,e: Event) {
      const $this = $(this);
      if ($this.closest('li').find('.collapse').hasClass('show')) {
        $this.removeClass('active');
      } else {
        $this.addClass('active');
      }
      e.preventDefault();
    });

    $(window).resize(function (this: Element) {
      const w = $(this).width();
      if (w > 768 && $('body').hasClass('offcanvas-menu')) {
        $('body').removeClass('offcanvas-menu');
      }
    });

    $('body').on('click', '.js-menu-toggle', function (this: Element, e:Event) {
      e.preventDefault();
      const $this = $(this);
      $('body').toggleClass('offcanvas-menu');
      $this.toggleClass('active');
    });

    $(document).mouseup(function (this: Element, e:Event) {
      const container = $('.site-mobile-menu');
      if (!container.is(e.target) && container.has(e.target).length === 0) {
        $('body').removeClass('offcanvas-menu');
      }
    });
  }

  siteIsotope() {
    const $container = $('#posts').isotope({
      itemSelector: '.item',
      isFitWidth: true
    });

    $(window).resize(function () {
      $container.isotope({
        columnWidth: '.col-sm-3'
      });
    });

    $container.isotope({ filter: '*' });

    $('#filters').on('click', 'button', function (this: Element, e:Event) {
      e.preventDefault();
      const filterValue = $(this).attr('data-filter');
      $container.isotope({ filter: filterValue });
      $('#filters button').removeClass('active');
      $(this).addClass('active');
    });
  }

  fancyBoxInit() {
    $('.fancybox').on('click', function (this: Element) {
      const visibleLinks = $('.fancybox');
      $.fancybox.open(visibleLinks, {}, visibleLinks.index(this));
      return false;
    });
  }

  stickyFillInit() {
    const recalc = () => {
      if ($('.jm-sticky-top').length > 0) {
        Stickyfill.add($('.jm-sticky-top'));
      }
    };

    $(window).on('resize orientationchange', recalc).resize();
  }

  onePageNavigation() {
    $('body').on('click', '.main-menu li a[href^="#"], .smoothscroll[href^="#"], .site-mobile-menu .site-nav-wrap li a', function (this: HTMLAnchorElement, e:Event) {
      e.preventDefault();
      const hash = this.hash;
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 600, 'easeInOutCirc', function () {
        window.location.hash = hash;
      });
    });
  }

  counterInit() {
    if ($('.section-counter').length > 0) {
      $('.section-counter').waypoint(function (this: HTMLElement, direction: any) {
        // Sử dụng this là HTMLElement
        if (direction === 'down' && !$(this).hasClass('ftco-animated')) {
          const comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',');
          $('.number').each(() => {
            const $this = $(this);
            const num = $this.data('number');
            $this.animateNumber({
              number: num,
              numberStep: comma_separator_number_step
            }, 7000);
          });
        }
      }, { offset: '95%' });
    }
  }


  selectPickerInit() {
    $('.selectpicker').selectpicker();
  }

  owlCarouselInit() {
    $('.single-carousel').owlCarousel({
      loop: true,
      margin: 0,
      autoplay: true,
      items: 1,
      smartSpeed: 1000
    });
  }

  quillInit() {
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike'],
      ['blockquote', 'code-block'],
      [{ 'header': 1 }, { 'header': 2 }],
      [{ 'list': 'ordered' }, { 'list': 'bullet' }],
      [{ 'script': 'sub' }, { 'script': 'super' }],
      [{ 'indent': '-1' }, { 'indent': '+1' }],
      [{ 'direction': 'rtl' }],
      [{ 'size': ['small', false, 'large', 'huge'] }],
      [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
      [{ 'color': [] }, { 'background': [] }],
      [{ 'font': [] }],
      [{ 'align': [] }],
      ['clean']
    ];

    if ($('.editor').length > 0) {
      new Quill('#editor-1', {
        modules: { toolbar: toolbarOptions },
        placeholder: 'Compose an epic...',
        theme: 'snow'
      });
      new Quill('#editor-2', {
        modules: { toolbar: toolbarOptions },
        placeholder: 'Compose an epic...',
        theme: 'snow'
      });
    }
  }
}

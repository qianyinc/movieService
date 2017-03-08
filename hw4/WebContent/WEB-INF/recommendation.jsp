<head>
    <meta charset="utf-8">
    <title>Movie Recommendation list</title>

    <!-- Bootstrap 3 -->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <style type="text/css" media="screen">
        body {
            padding-top: 80px;
        }
        #trailer .modal-dialog {
            margin-top: 200px;
            width: 640px;
            height: 480px;
        }
        .hanging-close {
            position: absolute;
            top: -12px;
            right: -12px;
            z-index: 9001;
        }
        #trailer-video {
            width: 100%;
            height: 100%;
        }
        .movie-tile {
            margin-bottom: 20px;
            padding-top: 20px;
        }
        .movie-tile:hover {
            background-color: #EEE;
            cursor: pointer;
        }
        .scale-media {
            padding-bottom: 56.25%;
            position: relative;
        }
        .scale-media iframe {
            border: none;
            height: 100%;
            position: absolute;
            width: 100%;
            left: 0;
            top: 0;
            background-color: white;
        }
    </style>
    <script type="text/javascript" charset="utf-8">
        // Pause the video when the modal is closed
        $(document).on('click', '.hanging-close, .modal-backdrop, .modal', function (event) {
            // Remove the src so the player itself gets removed, as this is the only
            // reliable way to ensure the video stops playing in IE
            $("#trailer-video-container").empty();
        });
        // Start playing the video whenever the trailer modal is opened
        $(document).on('click', '.movie-tile', function (event) {
            var trailerYouTubeId = $(this).attr('data-trailer-youtube-id')
            var sourceUrl = 'http://www.youtube.com/embed/' + trailerYouTubeId + '?autoplay=1&html5=1';
            $("#trailer-video-container").empty().append($("<iframe></iframe>", {
              'id': 'trailer-video',
              'type': 'text-html',
              'src': sourceUrl,
              'frameborder': 0
            }));
        });
        // Animate in the movies when the page loads
        $(document).ready(function () {
          $('.movie-tile').hide().first().show("fast", function showNext() {
            $(this).next("div").show("fast", showNext);
          });
        });
    </script>
</head>

<!DOCTYPE html>
<html lang="en">
  <body>
    <!-- Trailer Video Modal -->
    <div class="modal" id="trailer">
      <div class="modal-dialog">
        <div class="modal-content">
          <a href="#" class="hanging-close" data-dismiss="modal" aria-hidden="true">
            <img src="https://lh5.ggpht.com/v4-628SilF0HtHuHdu5EzxD7WRqOrrTIDi_MhEG6_qkNtUK5Wg7KPkofp_VJoF7RS2LhxwEFCO1ICHZlc-o_=s0#w=24&h=24"/>
          </a>
          <div class="scale-media" id="trailer-video-container">
          </div>
        </div>
      </div>
    </div>
    
    <!-- Main Page Content -->
    <div class="container">
      <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">Yuhan's favorite movie list</a>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      
<div class="col-md-6 col-lg-4 movie-tile text-center" data-trailer-youtube-id="W6iVPCRflQM" data-toggle="modal" data-target="#trailer">
    <img src="https://s-media-cache-ak0.pinimg.com/originals/27/54/d8/2754d819bb6cb4a9787bb8a25bf09564.jpg" width="220" height="342">
    <h2>Lost in translation</h2>
</div>

<div class="col-md-6 col-lg-4 movie-tile text-center" data-trailer-youtube-id="8LuxOYIpu-I" data-toggle="modal" data-target="#trailer">
    <img src="http://img.goldposter.com/2015/04/Trainspotting_poster_goldposter_com_9.jpg" width="220" height="342">
    <h2>Transpotting</h2>
</div>

<div class="col-md-6 col-lg-4 movie-tile text-center" data-trailer-youtube-id="ZSExdX0tds4" data-toggle="modal" data-target="#trailer">
    <img src="https://store-media.nytimes.com/store/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/0/3/03466us02.jpg" width="220" height="342">
    <h2>metropolis</h2>
</div>

<div class="col-md-6 col-lg-4 movie-tile text-center" data-trailer-youtube-id="ne6p6MfLBxc" data-toggle="modal" data-target="#trailer">
    <img src="http://www.newdvdreleasedates.com/images/posters/large/her-2013-10.jpg" width="220" height="342">
    <h2>Her</h2>
</div>

<div class="col-md-6 col-lg-4 movie-tile text-center" data-trailer-youtube-id="FAfR8omt-CY" data-toggle="modal" data-target="#trailer">
    <img src="https://upload.wikimedia.org/wikipedia/en/9/9f/Midnight_in_Paris_Poster.jpg" width="220" height="342">
    <h2>Midnight in Paris</h2>
</div>

<div class="col-md-6 col-lg-4 movie-tile text-center" data-trailer-youtube-id="gyKqHOgMi4g" data-toggle="modal" data-target="#trailer">
    <img src="https://s-media-cache-ak0.pinimg.com/originals/5d/9f/92/5d9f923f3e51fc0d6cd54a1e60b1f86e.jpg" width="220" height="342">
    <h2>Ex Machina</h2>
</div>

    </div>
  </body>
</html>

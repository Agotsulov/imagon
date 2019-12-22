import torch
import matplotlib.pyplot as plt
import numpy as np 
import argparse
import pickle 
import os
from torchvision import transforms 
from build_vocab import Vocabulary
from model import EncoderCNN, DecoderRNN
from PIL import Image
import torch.nn as nn


class BFM(nn.Module):
    def __init__(self, args, vocab_len):
        super(BFM, self).__init__()
        self.encoder = EncoderCNN(args.embed_size).eval().cpu() 
        self.encoder.load_state_dict(torch.load('encoder.ckpt', map_location=torch.device('cpu')))
        self.decoder = DecoderRNN(args.embed_size, args.hidden_size, vocab_len, args.num_layers).eval().cpu() 
        self.decoder.forward = self.decoder.sample
        self.decoder.load_state_dict(torch.load('decoder.ckpt', map_location=torch.device('cpu')))
        
    def forward(self, image):
        feature = self.encoder(image)
        sampled_ids = self. decoder(feature)
        return sampled_ids


def main(args):
    with open(args.vocab_path, 'rb') as f:
        vocab = pickle.load(f)

    with open("./words.txt", 'w') as f:
        for i in vocab.idx2word.values():
            f.write(f'{i}\n')


    example = torch.rand(1, 3, 224, 224)
    bfm = BFM(args, len(vocab)).eval().cpu()  
    traced_script_module = torch.jit.trace(bfm, example)
    traced_script_module.save("./model.pt")


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--encoder_path', type=str, default='models/encoder-5-3000.pkl', help='path for trained encoder')
    parser.add_argument('--decoder_path', type=str, default='models/decoder-5-3000.pkl', help='path for trained decoder')
    parser.add_argument('--vocab_path', type=str, default='data/vocab.pkl', help='path for vocabulary wrapper')
    parser.add_argument('--embed_size', type=int , default=256, help='dimension of word embedding vectors')
    parser.add_argument('--hidden_size', type=int , default=512, help='dimension of lstm hidden states')
    parser.add_argument('--num_layers', type=int , default=2, help='number of layers in lstm')
    args = parser.parse_args()
    main(args)